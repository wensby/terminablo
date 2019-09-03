package com.wensby.terminablo.scene.mainmenu;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.wensby.application.userinterface.*;
import com.wensby.terminablo.scene.View;
import com.wensby.terminablo.userinterface.component.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainMenuView implements View {

  private final MainMenuModel model;
  private final InterfaceComponentFactory componentFactory;

  public MainMenuView(MainMenuModel model, InterfaceComponentFactory componentFactory) {
    this.model = requireNonNull(model);
    this.componentFactory = Objects.requireNonNull(componentFactory);
  }

  @Override
  public void render(TerminalLayerPainter painter) {
    var menuItems = model.getMenuItems().stream().map(this::createButton).collect(toList());
    var childLayout =
        "_ _ _\n" +
        "_ menu _\n" +
        "_ _ _\n" +
        "_ submenu _";
    var columnLayout = List.of(1, 1, 1);
    var rowLayout = List.of(4, 2, 1, 1);
    var grid = new Grid(Map.of(
        "menu", createFirstPartMenu(menuItems),
        "submenu", createSecondPartMenu(menuItems)
    ), childLayout, columnLayout, rowLayout);
    grid.render(painter);
  }

  private InterfaceComponent createButton(String item) {
    return componentFactory.createButton(item, model.getSelectedMenu().equals(item));
  }

  private Container createFirstPartMenu(List<InterfaceComponent> menuItems) {
    var containerBuilder = componentFactory.buildContainer();
    menuItems.subList(0, 3).forEach(containerBuilder::add);
    return containerBuilder.build();
  }

  private Container createSecondPartMenu(List<InterfaceComponent> menuItems) {
    var containerBuilder = componentFactory.buildContainer();
    containerBuilder.add(createCreditsCinematics(menuItems));
    containerBuilder.add(menuItems.get(5));
    return containerBuilder.build();
  }

  private InterfaceComponent createCreditsCinematics(List<InterfaceComponent> menuItems) {
    Map<String, InterfaceComponent> childrenByGridKey = Map.of(
        "credits", menuItems.get(3),
        "cinematics", menuItems.get(4)
    );
    return new Grid(childrenByGridKey, "credits cinematics", List.of(1, 1), List.of(1));
  }
}

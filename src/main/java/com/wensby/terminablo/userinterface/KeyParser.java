package com.wensby.terminablo.userinterface;

import static com.wensby.terminablo.ListUtil.truncateList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.OptionalInt;

class KeyParser {

  private final List<Integer> subjectBytes;

  KeyParser(List<Integer> bytes) {
    requireNonNull(bytes);
    subjectBytes = new ArrayList<>(bytes);
  }

  Key parseKey() {
    var candidates = getAllCandidates();
    validateCandidatePresent(candidates);
    var greedyCandidates = filterGreedyCandidates(candidates);
    validateUniqueGreedyCandidate(greedyCandidates);
    return greedyCandidates.iterator().next();
  }

  private List<Key> getAllCandidates() {
    return EnumSet.allOf(Key.class).stream()
        .filter(this::isPresentInBeginningOfBytes)
        .collect(toList());
  }

  private void validateCandidatePresent(List<Key> candidates) {
    if (candidates.isEmpty()) {
      throw new RuntimeException("Found no candidate Key for bytes list: " + subjectBytes);
    }
  }

  private List<Key> filterGreedyCandidates(List<Key> candidates) {
    var byteCount = getBiggestKeyByteCount(candidates)
        .orElseThrow(() -> new RuntimeException("Unexpected missing candidate."));
    return candidates.stream()
        .filter(key -> key.getBytes().size() == byteCount)
        .collect(toList());
  }

  private void validateUniqueGreedyCandidate(List<Key> greedyCandidates) {
    if (greedyCandidates.size() != 1) {
      throw new RuntimeException(
          "Found no unique greedy candidate Key for bytes list: " + subjectBytes);
    }
  }

  private boolean isPresentInBeginningOfBytes(Key key) {
    var keyBytes = key.getBytes();
    var keyBytesCount = keyBytes.size();
    var truncatedList = truncateList(subjectBytes, keyBytesCount);
    return truncatedList.equals(keyBytes);
  }

  private OptionalInt getBiggestKeyByteCount(List<Key> keys) {
    return keys.stream()
        .mapToInt(key -> key.getBytes().size())
        .max();
  }
}

package com.wensby.terminablo.userinterface;

import static com.wensby.terminablo.ListUtil.truncateList;
import static java.util.stream.Collectors.toList;

import java.util.EnumSet;
import java.util.List;

class KeyParser {

  private final List<Integer> subjectBytes;

  KeyParser(List<Integer> bytes) {
    subjectBytes = List.copyOf(bytes);
  }

  Key parseKey() {
    var candidates = getCandidateKeys();
    validatePresent(candidates);
    return getHighestByteCountKeyCandidate(candidates);
  }

  private List<Key> getCandidateKeys() {
    return EnumSet.allOf(Key.class).stream()
        .filter(this::isSubjectBytesBeginningEqualToKeyBytes)
        .collect(toList());
  }

  private boolean isSubjectBytesBeginningEqualToKeyBytes(Key key) {
    return isBeginningEqual(subjectBytes, key.getBytes());
  }

  private void validatePresent(List<Key> candidates) {
    if (candidates.isEmpty()) {
      throw new RuntimeException("Found no candidate Key for bytes list: " + subjectBytes);
    }
  }

  private Key getHighestByteCountKeyCandidate(List<Key> candidates) {
    var highestByteCount = getHighestKeyByteCount(candidates);
    var greedyCandidates = candidates.stream()
        .filter(key -> key.getBytes().size() == highestByteCount)
        .collect(toList());
    if (greedyCandidates.size() != 1) {
      throw new RuntimeException(
          "Found no unique greedy candidate Key for bytes list: " + subjectBytes);
    }
    return greedyCandidates.iterator().next();
  }

  private boolean isBeginningEqual(List<?> subject, List<?> beginning) {
    return truncateList(subject, beginning.size()).equals(beginning);
  }

  private int getHighestKeyByteCount(List<Key> keys) {
    return keys.stream()
        .mapToInt(key -> key.getBytes().size())
        .max()
        .orElseThrow(() -> new RuntimeException("Unexpected missing candidate."));
  }
}

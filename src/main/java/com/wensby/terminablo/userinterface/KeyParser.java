package com.wensby.terminablo.userinterface;

import java.util.*;

import static com.wensby.terminablo.ListUtil.truncateList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

class KeyParser {

    private final List<Integer> subjectBytes;

    KeyParser(List<Integer> bytes) {
        requireNonNull(bytes);
        subjectBytes = new ArrayList<>(bytes);
    }

    Key parseKey() {
        List<Key> candidates = getAllCandidates();
        validateCandidatePresent(candidates);
        List<Key> greedyCandidates = filterGreedyCandidates(candidates);
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
        int byteCount = getBiggestKeyByteCount(candidates)
                .orElseThrow(() -> new RuntimeException("Unexpected missing candidate."));
        return candidates.stream()
                .filter(key -> key.getBytes().size() == byteCount)
                .collect(toList());
    }

    private void validateUniqueGreedyCandidate(List<Key> greedyCandidates) {
        if (greedyCandidates.size() != 1) {
            throw new RuntimeException("Found no unique greedy candidate Key for bytes list: " + subjectBytes);
        }
    }

    private boolean isPresentInBeginningOfBytes(Key key) {
        List<Integer> keyBytes = key.getBytes();
        int keyBytesCount = keyBytes.size();
        List<Integer> truncatedList = truncateList(subjectBytes, keyBytesCount);
        return truncatedList.equals(keyBytes);
    }

    private OptionalInt getBiggestKeyByteCount(List<Key> keys) {
        return keys.stream()
                .mapToInt(key -> key.getBytes().size())
                .max();
    }
}

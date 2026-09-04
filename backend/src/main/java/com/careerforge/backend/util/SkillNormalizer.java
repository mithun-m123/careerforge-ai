package com.careerforge.backend.util;

import java.util.Map;

public class SkillNormalizer {

    private static final Map<String, String> ALIASES = Map.of(
            "js", "javascript",
            "javascript", "javascript",

            "springboot", "spring boot",
            "spring boot", "spring boot",

            "reactjs", "react",
            "react.js", "react",

            "nodejs", "node.js",
            "node.js", "node.js",

            "ts", "typescript",
            "typescript", "typescript"
    );

    private SkillNormalizer() {
    }

    public static String normalize(String skill) {

        if (skill == null) {
            return "";
        }

        String normalized =
                skill.trim().toLowerCase();

        return ALIASES.getOrDefault(
                normalized,
                normalized
        );
    }
}
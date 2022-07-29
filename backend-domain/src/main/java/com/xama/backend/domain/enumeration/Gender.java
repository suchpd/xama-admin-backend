package com.xama.backend.domain.enumeration;

/**
 * 性别
 */
public enum Gender {
    Male("男"),
    Female("女");

    private String name;

    Gender(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public static Gender getByEnumName(String enumName) {
        for (Gender gender : Gender.values()) {
            if (gender.name().equals(enumName)) {
                return gender;
            }
        }
        return null;
    }
}

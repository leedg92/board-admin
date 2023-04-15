package com.example.boardandadmin.domain.converter;

import com.example.boardandadmin.domain.constant.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class RoleTypesConverter implements AttributeConverter<Set<RoleType>, String> {

    private static final String DELIMITER = ",";
    @Override
    public String convertToDatabaseColumn(Set<RoleType> attribute) {
        return attribute.stream().map(RoleType::name).sorted().collect(Collectors.joining(DELIMITER));
    }

    @Override
    public Set<RoleType> convertToEntityAttribute(String dbData) {

//        String[] roles = dbData.split(DELIMITER);
//        Set<RoleType> roleSet = new HashSet<>();
//        for (String role : roles) {
//            RoleType roleType = RoleType.valueOf(role);
//            roleSet.add(roleType);
//        }
//        return roleSet;
// ==
//        return Arrays.stream(dbData.split(DELIMITER)).map(x->RoleType.valueOf(x)).collect(Collectors.toSet());
// ==
        return Arrays.stream(dbData.split(DELIMITER)).map(RoleType::valueOf).collect(Collectors.toSet());
    }
}

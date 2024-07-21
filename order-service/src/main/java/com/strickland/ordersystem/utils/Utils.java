package com.strickland.ordersystem.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public enum Utils {
    ;
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().findAndRegisterModules();
}

package com.example.jsonrpc.demo;

import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;


@JsonRpcService("/api")
public interface ServerAPI {
    @JsonRpcMethod("concate_strings")
    String concateStrings(@JsonRpcParam(value = "first_string") String firstString, @JsonRpcParam(value = "second_string") String secondString);
}
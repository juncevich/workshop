package com.example.jsonrpc.demo;

import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.springframework.stereotype.Service;

@Service
@AutoJsonRpcServiceImpl
public class ServerAPIImpl implements ServerAPI {
    @Override
    public String concateStrings(String firstString, String secondString) {
        return firstString + " " + secondString;
    }
}

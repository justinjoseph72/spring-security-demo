package com.justin.app.apisecurity.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class ShellItem {

    @ShellMethod("this is a test")
    public String translate(){
        return "justin";
    }
}

package io.github.kituin.kungfu.items.miji;

public class GitmijiList {
    public GitmijiList(){

    }
    public String select(String name){
        switch(name){
            case "miji.yijinjing":
                return "1";
        }
        return "NULL";
    }
}

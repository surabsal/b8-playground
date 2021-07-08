package pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlackPojo {

    private boolean ok;
    private String channel;
    private SlackMessagePojo message;

}

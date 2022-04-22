package model;

import enums.MessageSubject;
import lombok.Data;

@Data //lombok
public class Message {
    private MessageSubject subject; //enums.MessageSubject
    private String email;
    private String orderReference;
    private String message;




}

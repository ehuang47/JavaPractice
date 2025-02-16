package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.domain.Message;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.repository.message.MessageRepository;
import com.example.d5_mvc_jsp_quiz.utils.TimeUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends EntityService<Message, Long> {
  public MessageService(MessageRepository repository) {
    super(repository);
  }

  public Message bodyMapper(Message contactMessage){
    if (contactMessage.getSubject() == null) {
      throw new InvalidArgumentException("Subject is required.");
    } else if (contactMessage.getEmail() == null) {
      throw new InvalidArgumentException("Email is required.");
    } else if (contactMessage.getMessage() == null) {
      throw new InvalidArgumentException("Message is required.");
    }
    String dateSubmitted = TimeUtils.getCurrentTimestamp();
    contactMessage.setDateSubmitted(dateSubmitted);
    return contactMessage;
  }
}

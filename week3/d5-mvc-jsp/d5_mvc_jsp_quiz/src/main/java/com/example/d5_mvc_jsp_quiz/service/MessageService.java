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

  public Message bodyMapper(String subject, String email, String messageContent){
    if (subject == null) {
      throw new InvalidArgumentException("Subject is required.");
    } else if (email == null) {
      throw new InvalidArgumentException("Email is required.");
    } else if (messageContent == null) {
      throw new InvalidArgumentException("Message is required.");
    }
    Message message = new Message();
    message.setSubject(subject);
    message.setEmail(email);
    message.setMessage(messageContent);
    String dateSubmitted = TimeUtils.getCurrentTimestamp();
    message.setDateSubmitted(dateSubmitted);

    return message;
  }
}

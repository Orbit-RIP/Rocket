package rip.orbit.rocket.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xanderume@gmail (JavaProject)
 */
@AllArgsConstructor
class ChatMessage {

    @Getter private long sentAt;
    @Getter private String message;

}

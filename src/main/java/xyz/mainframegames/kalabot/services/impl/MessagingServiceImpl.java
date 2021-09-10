package xyz.mainframegames.kalabot.services.impl;

import org.javacord.api.entity.Icon;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.springframework.stereotype.Component;
import xyz.mainframegames.kalabot.services.MessagingService;

import java.awt.*;
import java.io.File;

@Component
public class MessagingServiceImpl implements MessagingService {
    @Override
    public void sendMessage(MessageAuthor author, String title, String description, String footer, Icon thumbnail, Color color, TextChannel channel) {
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(author)
                .setTitle(title)
                .setDescription(description)
                .setFooter(footer)
                .setThumbnail(thumbnail)
                .setColor(color))
                .send(channel);
    }
    public void sendMessage(MessageAuthor author, String title, String description, String footer, String thumbnail, Color color, TextChannel channel) {
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setAuthor(author)
                .setTitle(title)
                .setDescription(description)
                .setFooter(footer)
                .setThumbnail(thumbnail)
                .setColor(color))
                .send(channel);
    }
    public void sendImage(Icon image, TextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setImage(image)
                ).send(channel);
    }
    public void sendImage(String image, TextChannel channel){
        new MessageBuilder().setEmbed(new EmbedBuilder()
                .setImage(image)
                ).send(channel);
    }
    //Method to format the user IDs that the bot gets from a mention
    //Formatted User ID example: 123456789123456789
    //All of them have 18 numbers that represent the ID but when doing a mention the ID given can look like this
    //Example 1: <@!123456789123456789>
    //Example 2: <@123456789123456789>
    //So to use the User ID first it needs to be formatted based on what example is the case
    public long formatUserId(String word){
        long userId;
        //If the user id has a ! the substring is different
        if(!Character.isDigit(word.charAt(2))) {
            userId = Long.parseLong(word.substring(3, word.length() - 1));

        } else {
            userId = Long.parseLong(word.substring(2, word.length() - 1));
        }
        return userId;
    }
}
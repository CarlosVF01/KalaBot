package xyz.mainframegames.kalabot.commands.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import xyz.mainframegames.kalabot.audioplayer.AudioManager;
import xyz.mainframegames.kalabot.commands.AbstractCommand;
import xyz.mainframegames.kalabot.services.messages.MessagingService;
import xyz.mainframegames.kalabot.utils.BotError;
import xyz.mainframegames.kalabot.utils.Command;

public class PauseCommand extends AbstractCommand {

  private static final String PAUSED = "The player has been paused";

  public PauseCommand(MessagingService messagingService) {
    super(Command.PAUSE.toString(), messagingService);
  }

  @Override
  protected void runCommand(
      MessageCreateEvent event, Server server, ServerTextChannel channel, User user) {
    server
        .getAudioConnection()
        .ifPresentOrElse(
            connection -> {
              AudioPlayer audioPlayer = AudioManager.getServerManager(server.getId()).player;
              if (audioPlayer.getPlayingTrack() == null) {
                event.getChannel().sendMessage(BotError.NOT_PLAYING_MUSIC.getDescription());
              } else {
                audioPlayer.setPaused(true);
                event.getChannel().sendMessage(PAUSED);
              }
            },
            () -> event.getChannel().sendMessage(BotError.NOT_PLAYING_MUSIC.getDescription()));
  }
}

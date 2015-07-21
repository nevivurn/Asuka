package io.github.likcoras.asuka.handler.response;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import io.github.likcoras.asuka.AsukaBot;
import io.github.likcoras.asuka.BotUtil;
import lombok.NonNull;

public class MangaUpdatesResponse implements BotResponse {

	private static final String FORMAT = BotUtil
			.addFormat("&bName:&b %s | &bAuthor:&b %s | &bTags:&b %s | &bLast Release:&b %s | &bLink:&b %s");

	private GenericMessageEvent<PircBotX> event;
	private String message;

	public MangaUpdatesResponse(@NonNull GenericMessageEvent<PircBotX> event, @NonNull String title, @NonNull String author, @NonNull String genres,
			String lastRelease, String link) {
		this.event = event;
		message = String.format(FORMAT, title, author, genres, lastRelease, link);
	}

	@Override
	public void send(AsukaBot bot) {
		if (event instanceof MessageEvent)
			((MessageEvent<PircBotX>) event).getChannel().send().message(message);
		else
			event.respond(message);
	}

}

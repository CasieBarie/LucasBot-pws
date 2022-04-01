package me.lucas;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Stats {
	public void stats(JDA jda) {
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		Runnable task = () -> {
			for(Guild guild : jda.getGuilds()) {
				guild.getVoiceChannelById(959430575465304104L).getManager().setName("Leden aantal: " + guild.getMembers().size()).queue();

			}
		}; executorService.scheduleWithFixedDelay(task, 0, 1, TimeUnit.MINUTES);
	}
}
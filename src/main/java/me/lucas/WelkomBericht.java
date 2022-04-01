package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class WelkomBericht extends ListenerAdapter {
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent e) {e.getGuild().getTextChannelById(959426161899888732L).sendMessageEmbeds(welkom(e.getMember())).queue();}
	private MessageEmbed welkom(Member member) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(":wave: WELKOM! :wave:");
		eb.setDescription(String.format("Welkom %s!\nVeel plezier op de server!", member.getAsMention()));
		eb.setColor(Color.BLUE);
		eb.setThumbnail(member.getEffectiveAvatarUrl());
		return eb.build();
	}
}
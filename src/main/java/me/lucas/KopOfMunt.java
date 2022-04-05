package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.awt.*;
import java.util.Random;

public class KopOfMunt {
	Random random = new Random();
	public void kopOfMunt(SlashCommandEvent e) {e.replyEmbeds(kopOfMuntEmbed((random.nextBoolean()))).setEphemeral(true).queue();}
	private MessageEmbed kopOfMuntEmbed(Boolean kopofmunt) {
		EmbedBuilder eb = new EmbedBuilder();
		//Er word random een van de twee afbeeldingen gebruikt.
		eb.setImage((kopofmunt) ? "https://medialabeemland.nl/wp-content/uploads/2021/02/Euromunt-1.png" : "https://kuleuvenblogt.files.wordpress.com/2015/12/obverse.png");
		eb.setColor(Color.MAGENTA);
		return eb.build();
	}
}
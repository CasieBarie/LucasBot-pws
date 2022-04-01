package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class Koekje extends ListenerAdapter {
	public void koekjeCommand(SlashCommandEvent e) {
		//Hiermee krijg je het 'koekje' role met het id 957570955905998848.
		Role role = e.getGuild().getRoleById(957570955905998848L);
		//Bekijk of de verzender de role al heeft.
		if(!e.getMember().getRoles().contains(role)) {
			//Verwijder het command bericht.
			e.reply("").queue(m -> m.deleteOriginal().queue());
			//Stuur het bericht met een kadertje voor als het koekje is gegeven.
			e.getChannel().sendMessageEmbeds(koekjeEmbed(e.getUser(), role)).queue();
			//Voeg de role toe aan de verzender.
			e.getGuild().addRoleToMember(e.getMember(), role).queue();
		} else {
			//Als de verzender de role al heeft stuur dan bericht dat hij al een koekje heeft gegeven.
			//.setEphemeral zorgt ervoor dat het bericht alleen naar de verzender word gestuurd.
			e.reply("Je hebt mij al een koekje gegeven!").setEphemeral(true).queue();
		}
	}

	//Dit maakt een bericht met een kadertje voor als het koekje is gegeven.
	private MessageEmbed koekjeEmbed(User user, Role role) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setTitle(":cookie: KOEKJE :cookie:");
		eb.setColor(new Color(0xb5743f));
		eb.setDescription(String.format("**Bedankt %s!**\nIk heb erg genoten van dit koekje!\nAls bedankje heb je nu de %s role gekregen!", user.getAsMention(), role.getAsMention()));
		//Dit voegt een plaatje van een koekje in het bericht.
		eb.setThumbnail("https://i.imgur.com/xzBbaym.png");
		return eb.build();
	}
}
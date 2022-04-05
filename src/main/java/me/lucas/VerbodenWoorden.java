package me.lucas;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class VerbodenWoorden extends ListenerAdapter {
	//Een paar scheldwoorden.
	private enum woorden {
		Fuck, Kut, Godverdomme, Lul, Shit, Slet, Hoer, Nazi,
		Neger, Kanker, Flikker, Tjoela, Kech, Kinderverkrachter,
		Klootzak, Sukkel, Eikel, Homo, Aso, Trut, Doos, Kutwijf,
		Idioot, Mongool, Aapmens, Aarstulp, Adder, Afrotten, Apenjong,
		Apenkont, Autist, Bitch, Barslet, Lijer, doos, dwaas, etter,
		ezel, Flapdrol, flessentrekker, kaaskop, klaplul, klootviool,
		klootzak, kontneuker, kutwijf, lafbek, loser, luilak, miet,
		kkr, Kruujer, Koekwous, Cock, Ass, Cum, Cunt, Nigga, Piss, Prick, Twat
	}

	public void onMessageReceived(MessageReceivedEvent e) {
		Role waarschuwing1 = e.getGuild().getRoleById(960821489882521632L), waarschuwing2 = e.getGuild().getRoleById(960821579598667796L), waarschuwing3 = e.getGuild().getRoleById(960821643855405076L);
		String bericht = e.getMessage().getContentRaw();
		String nieuwBericht = bericht;
		for(woorden woord : woorden.values()) {
			//Kijkt hoelang het scheldwoord is en voegt zoveel sterretjes toe aan het bericht.
			String sterretjes = new String(new char[woord.name().length()]).replace("\0", "\\\\*");
			nieuwBericht = nieuwBericht.replaceAll("(?i)" + woord.name(), sterretjes);
		} if(!bericht.equals(nieuwBericht)) {
			//Dit verwijderd het bericht.
			e.getMessage().delete().queue();
			//Stuurt een waarschuwing in de channel.
			e.getChannel().sendMessageEmbeds(scheldwoord(e.getMember(), nieuwBericht)).queue();
			//Dit voegt de waarschuwing role toe aan de verzender.
			if(!e.getMember().getRoles().contains(waarschuwing1)) {e.getGuild().addRoleToMember(e.getMember(), waarschuwing1).queue();
			} else if(!e.getMember().getRoles().contains(waarschuwing2)) {e.getGuild().addRoleToMember(e.getMember(), waarschuwing2).queue();
			} else if(!e.getMember().getRoles().contains(waarschuwing3)) {e.getGuild().addRoleToMember(e.getMember(), waarschuwing3).queue();}
		}
	}

	private MessageEmbed scheldwoord(Member member, String bericht) {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setDescription(member.getAsMention() + " >> " + bericht);
		eb.setFooter("Je hebt een waarchuwing gekregen!");
		eb.setColor(Color.RED);
		return eb.build();
	}
}
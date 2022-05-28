package me.bufferoverflow.warden

import com.kotlindiscord.kord.extensions.ExtensibleBot
import com.kotlindiscord.kord.extensions.utils.env
import dev.kord.common.entity.PresenceStatus
import dev.kord.common.entity.Snowflake
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

val TEST_SERVER_ID = Snowflake(
    env("TEST_SERVER").toLong()  // Get the test server ID from the env vars or a .env file
)

private val TOKEN = env("TOKEN")   // Get the bot' token from the env vars or a .env file

@PrivilegedIntent
suspend fun main() {
    val bot = ExtensibleBot(TOKEN) {
        applicationCommands {
            defaultGuild(TEST_SERVER_ID)
            enabled = true
        }
        extensions {

        }
        presence {
            status = PresenceStatus.Online
            playing(getStatus())
        }
        intents {
            +Intent.GuildMembers
            +Intent.GuildMembers
            +Intent.Guilds
        }
    }

    bot.start()
}

private fun getStatus(): String {
    return listOf(
        "Ah, I think we'll get along just fine.",
        "Can't, never could!",
        "C'mon, c'mon! Sitting pretty...",
        "Come on! I feel like a bug on a griddle.",
        "Couldn't've said it better myself.",
        "Find yourself alone, you fall back. Heroes just get themselves killed.",
        "First dibs on any drone that comes in.",
        "Heh, that's me.",
        "Hey, remind me to tell you the one about the Pope and his donkey, after this.",
        "I feel smarter already.",
        "I reckon you're right.",
        "Out with the old, in with the new.",
        "Play it smart. And when that doesn't work, play dead, then get 'em from behind!",
        "Remember, prone gets ya home. And there's a reason nothing rhymes with 'rushing in like an asshole'.",
        "Remember, prone gets you home.",
        "Remind me to tell you the one about the astrologist's blind date, after this.",
        "Remind me to tell you the one about the bartender's son, after this.",
        "Remind me to tell you the one about the owl and the squirrel, after this.",
        "There's no shame in calling for backup. We fight as a team.",
        "This ain't no ice cream social.",
        "This is my least favorite part.",
        "Time's on our side, not theirs. All we have to do is survive.",
        "Well bless your heart.",
        "Well, well, well. This is boring.",
        "You ready? I'm ready. Yup, we're all ready."
    ).random()
}
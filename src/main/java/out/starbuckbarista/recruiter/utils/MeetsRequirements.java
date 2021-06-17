package out.starbuckbarista.recruiter.utils;

import com.google.gson.JsonObject;

import java.io.IOException;

public class MeetsRequirements {

    public static Boolean playerMeetsRequirements (String player) {

        try {

            HTTPClient httpClient = new HTTPClient("https://api.slothpixel.me/api/players/" + player);
            JsonObject data = httpClient.getRawResponse();

            Boolean online = data.get("online").getAsBoolean();

            if (online == null || !online) {

                return false;
            } else {

                JsonObject jsonObject = data.get("stats").getAsJsonObject();
                JsonObject bedWarsStats = jsonObject.get("BedWars").getAsJsonObject();
                JsonObject skyWarsStats = jsonObject.get("SkyWars").getAsJsonObject();
                JsonObject duelsStats = jsonObject.get("Duels").getAsJsonObject().get("general").getAsJsonObject();
                JsonObject bridgeStats = jsonObject.get("Duels").getAsJsonObject().get("gamemodes").getAsJsonObject()
                        .get("bridge").getAsJsonObject();

                JsonObject bridgeDuels = bridgeStats.get("duels").getAsJsonObject();
                JsonObject bridgeDoubles = bridgeStats.get("doubles").getAsJsonObject();
                JsonObject bridgeFours = bridgeStats.get("fours").getAsJsonObject();
                JsonObject bridge2v2v2v2 = bridgeStats.get("2v2v2v2").getAsJsonObject();
                JsonObject bridge3v3v3v3 = bridgeStats.get("3v3v3v3").getAsJsonObject();

                try {

                    long x = bedWarsStats.get("final_k_d").getAsBigDecimal().longValue();
                    int y = bedWarsStats.get("wins").getAsInt();
                } catch (Exception exception) {

                    bedWarsStats.addProperty("final_k_d", 0.0);
                    bedWarsStats.addProperty("wins", 0);
                }

                try {

                    long x = skyWarsStats.get("kill_death_ratio").getAsBigDecimal().longValue();
                    int y = skyWarsStats.get("kills").getAsInt();
                } catch (Exception exception) {

                    skyWarsStats.addProperty("kill_death_ratio", 0.0);
                    skyWarsStats.addProperty("kills", 0);
                }

                try {

                    long x = duelsStats.get("kd_ratio").getAsBigDecimal().longValue();
                    int y = duelsStats.get("wins").getAsInt();
                } catch (Exception exception) {

                    duelsStats.addProperty("kd_ratio", 0.0);
                    duelsStats.addProperty("wins", 0);
                }

                try {

                    int x = bridgeDuels.get("wins").getAsInt();
                    int y = bridgeDuels.get("losses").getAsInt();
                } catch (Exception exception) {

                    bridgeDuels.addProperty("wins", 0);
                    bridgeDuels.addProperty("losses", 0);
                }

                try {

                    int x = bridgeDoubles.get("wins").getAsInt();
                    int y = bridgeDoubles.get("losses").getAsInt();
                } catch (Exception exception) {

                    bridgeDoubles.addProperty("wins", 0);
                    bridgeDoubles.addProperty("losses", 0);
                }

                try {

                    int x = bridgeFours.get("wins").getAsInt();
                    int y = bridgeFours.get("losses").getAsInt();
                } catch (Exception exception) {

                    bridgeFours.addProperty("wins", 0);
                    bridgeFours.addProperty("losses", 0);
                }

                try {

                    int x = bridge2v2v2v2.get("wins").getAsInt();
                    int y = bridge2v2v2v2.get("losses").getAsInt();
                } catch (Exception exception) {

                    bridge2v2v2v2.addProperty("wins", 0);
                    bridge2v2v2v2.addProperty("losses", 0);
                }

                try {

                    int x = bridge3v3v3v3.get("wins").getAsInt();
                    int y = bridge3v3v3v3.get("losses").getAsInt();
                } catch (Exception exception) {

                    bridge3v3v3v3.addProperty("wins", 0);
                    bridge3v3v3v3.addProperty("losses", 0);
                }

                int bridgeWins = bridgeDuels.get("wins").getAsInt() + bridgeDoubles.get("wins").getAsInt() +
                        bridgeFours.get("wins").getAsInt() + bridge2v2v2v2.get("wins").getAsInt() +
                        bridge3v3v3v3.get("wins").getAsInt();
                int bridgeLosses = bridgeDuels.get("losses").getAsInt() + bridgeDoubles.get("losses").getAsInt() +
                        bridgeFours.get("losses").getAsInt() + bridge2v2v2v2.get("losses").getAsInt() +
                        bridge3v3v3v3.get("losses").getAsInt();

                if ((bedWarsStats.get("final_k_d").getAsBigDecimal().longValue() >= 6.0 && // Option 1
                        bedWarsStats.get("wins").getAsInt() >= 2000) ||
                        (bedWarsStats.get("final_k_d").getAsBigDecimal().longValue() >= 4.0 &&
                                bedWarsStats.get("wins").getAsInt() >= 5000)) {

                    return true;
                } else if ((bedWarsStats.get("final_k_d").getAsBigDecimal().longValue() >= 3.0 && // Option 2
                        bedWarsStats.get("wins").getAsInt() >= 1000) &&
                        ((skyWarsStats.get("kill_death_ratio").getAsBigDecimal().longValue() >= 1.5 &&
                                skyWarsStats.get("kills").getAsInt() >= 20000) ||
                                (duelsStats.get("kd_ratio").getAsBigDecimal().longValue() >= 3 &&
                                        duelsStats.get("wins").getAsInt() >= 10000) ||
                                ((bridgeWins / bridgeLosses) >= 10.0 && bridgeWins >= 2000))) {

                    return true;
                }
            }
        } catch (IOException e) {

            return false;
        }

        return false;
    }
}

package op.wawa.wilt.gui.alt;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import op.wawa.wilt.interfaces.IMixinMinecraft;

import javax.net.ssl.HttpsURLConnection;

public class AltLoginThread
extends Thread {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private final String password;
    private String status;
    private final String username;

    public AltLoginThread(String username, String password) {
        super("Alt Login Thread");
        this.username = username;
        this.password = password;
        this.status = "\u00a7eWaiting...";
    }

    private final Session createSession(String username, String password) {
        YggdrasilAuthenticationService service = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)service.createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(username);
        auth.setPassword(password);
        try {
            auth.logIn();
            return new Session(auth.getSelectedProfile().getName(), auth.getSelectedProfile().getId().toString(), auth.getAuthenticatedToken(), "mojang");
        }
        catch (AuthenticationException authenticationException) {
            return null;
        }
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public void run() {
        if (this.password.equals("")) {
            ((IMixinMinecraft) this.mc).setSession(new Session(this.username, mc.getSession().getPlayerID(), mc.getSession().getToken(), "legacy"));
            this.status = "\u00a7aLogged in. (" + this.username + " - offline name)";
            return;
        }
        this.status = "\u00a7eLogging in...";
        Session auth = this.createSession(this.username, this.password);
        if (auth == null) {
            this.status = "\u00a7cLogin failed!";
        } else {
            this.status = "\u00a7aLogged in. (" + auth.getUsername() + ")";
            ((IMixinMinecraft)this.mc).setSession(auth);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static String getUUID(String username) {
        try {
            // Make a http connection to Mojang API and ask for UUID of username
            HttpsURLConnection httpConnection = (HttpsURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + username).openConnection();
            httpConnection.setConnectTimeout(2000);
            httpConnection.setReadTimeout(2000);
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
            HttpURLConnection.setFollowRedirects(true);
            httpConnection.setDoOutput(true);

            if (httpConnection.getResponseCode() != 200)
                return "";

            // Read response content and get id from json
            try (InputStreamReader inputStream = new InputStreamReader(httpConnection.getInputStream())) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonElement = jsonParser.parse(inputStream).getAsJsonObject();

                if (jsonElement.isJsonObject()) {
                    return jsonElement.get("id").getAsString();
                }
            }
        } catch (Throwable ignored) {
        }

        return mc.getSession().getPlayerID();
    }
}


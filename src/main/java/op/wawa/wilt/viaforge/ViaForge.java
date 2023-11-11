package op.wawa.wilt.viaforge;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.platform.ViaPlatform;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import com.viaversion.viaversion.commands.ViaCommandHandler;
import com.viaversion.viaversion.protocols.base.BaseVersionProvider;
import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.raphimc.vialoader.ViaLoader;
import net.raphimc.vialoader.impl.platform.ViaBackwardsPlatformImpl;
import net.raphimc.vialoader.impl.platform.ViaRewindPlatformImpl;
import net.raphimc.vialoader.impl.viaversion.VLInjector;
import net.raphimc.vialoader.impl.viaversion.VLLoader;
import net.raphimc.vialoader.util.VersionEnum;

public class ViaForge {
    public static final VersionEnum NATIVE_VERSION;
    public static VersionEnum targetVersion;

    public ViaForge() {
    }

    public static void start() {
        VersionEnum.SORTED_VERSIONS.remove(VersionEnum.r1_7_6tor1_7_10);
        VersionEnum.SORTED_VERSIONS.remove(VersionEnum.r1_7_2tor1_7_5);
        ViaLoader.init(null, new VLLoader() {
            public void load() {
                super.load();
                Via.getManager().getProviders().use(VersionProvider.class, new BaseVersionProvider() {
                    public int getClosestServerProtocol(UserConnection connection) throws Exception {
                        return connection.isClientSide() && !Minecraft.getMinecraft().isSingleplayer() ? targetVersion.getVersion() : super.getClosestServerProtocol(connection);
                    }
                });
            }
        }, new VLInjector() {
            public String getDecoderName() {
                return "via-decoder";
            }

            public String getEncoderName() {
                return "via-encoder";
            }
        }, null, ViaBackwardsPlatformImpl::new, ViaRewindPlatformImpl::new);
    }

    static {
        NATIVE_VERSION = VersionEnum.r1_8;
        targetVersion = VersionEnum.r1_8;
    }
}

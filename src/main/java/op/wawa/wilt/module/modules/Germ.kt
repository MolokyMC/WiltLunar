package op.wawa.wilt.module.modules

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import net.minecraft.client.Minecraft
import net.minecraft.network.PacketBuffer
import net.minecraft.network.play.client.C17PacketCustomPayload
import net.minecraft.network.play.server.S3FPacketCustomPayload
import net.minecraft.util.ChatComponentText
import op.wawa.wilt.Wilt
import op.wawa.wilt.gui.germ.GermButton
import op.wawa.wilt.gui.germ.GermPage
import org.cubewhy.lunarcn.loader.api.event.PacketEvent
import org.cubewhy.lunarcn.loader.api.event.EventTarget
import org.yaml.snakeyaml.Yaml

class Germ {
    private val mc: Minecraft = Minecraft.getMinecraft()

    @EventTarget
    fun onPacket(event: PacketEvent.Receive){
        val packet = event.packet
        if (packet is S3FPacketCustomPayload) {
            if (packet.channelName.equals("germplugin-netease")) {
                process(packet.bufferData)
            }
        }
    }

    private fun process(byteBuf: ByteBuf) {
        //mc.thePlayer.addChatMessage(ChatComponentText("${Wilt.MOD_NAME} >> germplugin-netease"))
        val intMax = 9999999
        val packetBuffer = PacketBuffer(byteBuf)

        if (packetBuffer.readInt() != 73) return

        val buffer = PacketBuffer(packetBuffer.copy())
        val string: String = buffer.readStringFromBuffer(Short.MAX_VALUE.toInt())

        if (string.equals("gui", ignoreCase = true)) {

            val guiUuid: String = buffer.readStringFromBuffer(Short.MAX_VALUE.toInt())
            val yml: String = buffer.readStringFromBuffer(intMax)
            val yaml = Yaml()
            var map: Map<String, Any?>? = yaml.load(yml)

            if (map != null) map = map[guiUuid] as Map<String, Any?>?

            val buttons = ArrayList<GermButton>()

            if (map != null) {
                for (s in map.keys) {
                    if (s.equals("options", ignoreCase = true) || s.endsWith("_bg")) continue
                    var set = map[s] as Map<String, Any?>?

                    for (k in set!!.keys) {
                        if (!k.equals("scrollableParts", ignoreCase = true)) continue
                        set = set["scrollableParts"] as Map<String, Any?>?

                        for (uuid in set!!.keys) {
                            var objectMap = set[uuid] as Map<String, Any?>?
                            if (objectMap!!.containsKey("relativeParts")) {
                                objectMap = objectMap["relativeParts"] as Map<String, Any?>?
                                for (kk in objectMap!!.keys) {
                                    objectMap = objectMap[kk] as Map<String, Any?>?
                                    if (objectMap!!.containsKey("texts")) {
                                        val buttonText = (objectMap["texts"] as ArrayList<String?>?)!![0]
                                        buttons.add(GermButton("$s$$uuid$$kk", buttonText))
                                        break
                                    }
                                }
                            }
                        }

                        val sendBuffer = PacketBuffer(Unpooled.buffer().writeInt(4).writeInt(0).writeInt(0)).writeString(guiUuid).writeString(guiUuid).writeString(guiUuid)

                        mc.displayGuiScreen(GermPage(guiUuid, buttons))
                        mc.netHandler.addToSendQueue(C17PacketCustomPayload("germmod-netease", sendBuffer))

                        return
                    }
                }
            }
        }
    }
}
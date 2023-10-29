package op.wawa.wilt.module.modules

import io.netty.buffer.ByteBuf
import net.minecraft.client.Minecraft
import net.minecraft.network.play.server.S3FPacketCustomPayload
import op.wawa.wilt.gui.party.GuiHandleRequests
import op.wawa.wilt.gui.party.GuiInit
import op.wawa.wilt.gui.party.GuiInput
import op.wawa.wilt.gui.party.GuiPartyManage
import op.wawa.wilt.module.party.ButtonDecoder
import op.wawa.wilt.module.party.Sender
import org.cubewhy.lunarcn.loader.api.event.EventTarget
import org.cubewhy.lunarcn.loader.api.event.PacketEvent

class VexView {
    private val mc: Minecraft = Minecraft.getMinecraft()

    @EventTarget
    fun onPacket(event: PacketEvent.Receive){
        val packet = event.packet
        if (packet is S3FPacketCustomPayload) {
            if (packet.channelName.equals("VexView")) {
                process(packet.bufferData)
            }
        }
    }

    private fun process(byteBuf: ByteBuf?) {
        val buttonDecoder = ButtonDecoder(byteBuf)
        if (buttonDecoder.sign) {
            Sender.clickButton(buttonDecoder.getButton("sign").id)
        } else if (buttonDecoder.containsButton("手动输入")) {
            Sender.clickButton(buttonDecoder.getButton("手动输入").id)
        } else if (buttonDecoder.containsButton("提交")) { // 提交
            mc.displayGuiScreen(
                GuiInput(
                    buttonDecoder.getElement(buttonDecoder.getButtonIndex("提交") - 1),
                    buttonDecoder.getButton("提交")
                )
            )
        } else if (buttonDecoder.list) {
            mc.displayGuiScreen(GuiHandleRequests(buttonDecoder.requests))
        } else if (buttonDecoder.containsButtons("创建队伍", "申请入队")) {
            mc.displayGuiScreen(GuiInit(buttonDecoder.getButton("创建队伍"), buttonDecoder.getButton("申请入队")))
        } else if (buttonDecoder.containsButtons("申请列表", "申请列表", "踢出队员", "离开队伍", "解散队伍")) {
            if (buttonDecoder.containsButton("邀请玩家")) {
                mc.displayGuiScreen(
                    GuiPartyManage(
                        buttonDecoder.getButton("离开队伍"),
                        buttonDecoder.getButton("解散队伍"),
                        buttonDecoder.getButton("邀请玩家"),
                        buttonDecoder.getButton("申请列表")
                    )
                )
            } else {
                mc.displayGuiScreen(
                    GuiPartyManage(
                        buttonDecoder.getButton("离开队伍"),
                        buttonDecoder.getButton("解散队伍"),
                        null,
                        null
                    )
                )
            }
        } else if (buttonDecoder.containsButton("离开队伍")) {
            mc.displayGuiScreen(GuiPartyManage(buttonDecoder.getButton("离开队伍"), null, null, null))
        }
    }
}

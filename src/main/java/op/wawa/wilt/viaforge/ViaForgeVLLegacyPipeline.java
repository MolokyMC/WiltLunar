/*
 * This file is part of ViaForge - https://github.com/FlorianMichael/ViaForge
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package op.wawa.wilt.viaforge;

import com.viaversion.viaversion.api.connection.UserConnection;
import net.raphimc.vialoader.netty.VLLegacyPipeline;
import net.raphimc.vialoader.util.VersionEnum;

public class ViaForgeVLLegacyPipeline extends VLLegacyPipeline {
    public ViaForgeVLLegacyPipeline(UserConnection user, VersionEnum version) {
        super(user, version);
    }

    protected String decompressName() {
        return "decompress";
    }

    protected String compressName() {
        return "compress";
    }

    protected String packetDecoderName() {
        return "decoder";
    }

    protected String packetEncoderName() {
        return "encoder";
    }

    protected String lengthSplitterName() {
        return "splitter";
    }

    protected String lengthPrependerName() {
        return "prepender";
    }
}


package op.wawa.wilt.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.file.Files;

public class FileUtils {
    public static String readFile(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String readInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String line;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void unpackFile(File file, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        IOUtils.copy(FileUtils.class.getClassLoader().getResourceAsStream(name), (OutputStream)fos);
        fos.close();
    }
    public static void extractFile(ResourceLocation source, File destination) {
        try (InputStream in = Minecraft.getMinecraft().getResourceManager().getResource(source).getInputStream()) {
            if (in != null) {
                if (!destination.getParentFile().exists()) {
                    if (!destination.getParentFile().mkdirs()) {
                        throw new IOException();
                    }
                }
                Files.copy(in, destination.toPath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

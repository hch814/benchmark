package cc.kejun;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 生成大批量插入sql
 *
 * @author hch
 * @since 2021/9/4
 */
public class SqlGenerator {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        Random random = new Random();
        long phone = 13800000000L;
        try (FileWriter fileWriter = new FileWriter(new File("sql", "insert.sql"))) {
            for (int i = 0; i < 5000; i++) {
                StringBuilder stringBuilder = new StringBuilder("INSERT INTO pt_user(`username`,`gender`,`phone`,`email`,`address`,`create_time`,`update_time`,`creator`,`modifier`) VALUES\n");
                for (int j = 0; j < 1_000; j++) {
                    stringBuilder.append("(")
                            .append("'").append(RandomStringUtils.randomAlphanumeric(5, 16)).append("',")
                            .append(random.nextInt(2)).append(",")
                            .append(phone++).append(",")
                            .append("'").append(RandomStringUtils.randomAlphanumeric(5, 10)).append("@").append(RandomStringUtils.randomAlphanumeric(2, 8)).append(".com',")
                            .append("'").append(RandomStringUtils.randomAlphanumeric(15, 50)).append("',")
                            .append("now(),")
                            .append("now(),")
                            .append("'").append(RandomStringUtils.randomAlphanumeric(5, 16)).append("',")
                            .append("'").append(RandomStringUtils.randomAlphanumeric(5, 16)).append("'")
                            .append("),\n");
                }
                int index = stringBuilder.lastIndexOf(",");
                stringBuilder.replace(index, index + 1, ";");
                fileWriter.append(stringBuilder);
                fileWriter.flush();
            }
            System.out.printf("finished. cost: %ds%n", (System.currentTimeMillis() - start) / 1000);
        }
    }
}



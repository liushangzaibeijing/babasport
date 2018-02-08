package cn.babasport.xiu.core.controller.front;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.babasport.xiu.common.captcha.CreateCaptcha;

import cn.babasport.xiu.common.web.session.SessionProvider;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
/**
 * 生成验证码
 * @author xieqixiu
 *
 */
@Controller
public class JcaptchaController {

    @Autowired
    private SessionProvider sessionProvider;

    private final static char[] codeTable = {'2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final static Random rand = new Random();

    @RequestMapping(value = "/captcha.shtml")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer captchaBuf = new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            captchaBuf.append(codeTable[rand.nextInt(codeTable.length)]);
        }
        String captcha = captchaBuf.toString();
        System.out.println(captcha);

        sessionProvider.setAttribute(request, "captcha", captcha);

        BufferedImage img = CreateCaptcha.getCaptcha(captcha);
        try {
            response.setContentType("image/jpeg");
            response.setDateHeader("expries", -1);
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");

            ImageIO.write(img, "jpg", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

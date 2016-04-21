package top.latfat.mailcontrol;

import java.io.PrintWriter;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import top.latfat.mailcontrol.cmdexec.NativeCommandExecute;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class TestWindows {

	@Resource
	private NativeCommandExecute execute;
	
	@Test
	public void testExec() throws Throwable {
		execute.exec(new PrintWriter(System.out), "dir");
	}
}

package com.baicai.futurekiller;

import java.io.IOException;

public class ServerStarter {
	public static void main(String[] args) {
		FkServer server = new FkServer();
		server.start();
		try {
			new ShutDownThread(server, 9666).start();
		} catch (IOException e) {
			ServerLogger.error("ShutDownThread start error:", e);
			System.exit(-1);
		}
	}
}

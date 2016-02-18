package com.baicai.futurekiller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ShutDownThread extends Thread {
	private FkServer server;
	private ServerSocket shutdownSocket;

	ShutDownThread(FkServer server, int shutdownPort) throws IOException {
		super("ShutDownThread");
		this.server = server;
		this.shutdownSocket = new ServerSocket(shutdownPort);
		this.setDaemon(true);
		ServerLogger.info(String.format("listening shutdownPort %s", shutdownPort));
	}

	public void run() {
		Socket socketForShutdown = null;
		try {
			socketForShutdown = shutdownSocket.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(socketForShutdown.getInputStream()));
			String command = br.readLine();
			if (command.equals("shutdown")) {
				server.shutdown();
			}
		} catch (Exception e) {
			ServerLogger.error("ShutDownThread error:", e);
		}

		try {
			shutdownSocket.close();
		} catch (IOException e) {
			ServerLogger.error("ShutDownThread error:", e);
		}
	}

}

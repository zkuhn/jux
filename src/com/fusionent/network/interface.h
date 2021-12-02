// In LowsheenStmInterface (python still for now because of msg serialization)
// Messages are effectively dispatched by virtue of calling functions
/*

 def receive_scrubber_state(self):
     request_msg = self._create_request_packet()
 	 self._scrubber_state_channel.sendMessage(request_msg)
 	 bytes = self._scrubber_state_channel.getMessage()
 	 return ScrubberState.deserialize(bytes)


 def send_host_packet(self, host_packet):
 	 self._host_packet_channel.sendMessage(host_packet)

*/

#pragma once

// possibly wrap this with PythonMessageChannel for PyBind?
class MessageChannel{
	virtual void sendMessage(std::vector<uint8_t>);
	virtual std::vector<uint8_t> getMessage();
};

class UsbMessageChannel:MessageChannel{
	UsbMessageChannel(Usb usbConnection, int endpoint, int timeout);
	//add robustness about resetting connection or clearing dirty buffers in the body of this
	void sendMessage(std::vector<uint8_t>);
	std::vector<uint8_t> getMessage();
};

class UdpMessageChannel:MessageChannel{
	UdpMessageChannel(UdpConnection connection, int port, int timeout);
	void sendMessage(std::vector<uint8_t>);
	std::vector<uint8_t> getMessage();
};

// other channel types possible
/*
EthernetChannelWithUsbFallback
RetryingUsbChannel -- or just add retrying to base UsbChannel
I2C_Channel
FirewireChannel
SerialPortChannel
*/

//Then we just say how the channels are hooked up. "Dispatch" happens by automatically as mentioned above.
class ChannelProvider{
	virtual MessageChannel getScrubberStateChannel() = 0;
	virtual MessageChannel getConfigMessageChannel() = 0;
	virtual MessageChannel getHostPacketChannel() = 0;

};

namespace LowsheenUsbEndPoint{
	static const int SCRUBBER_STATE_ENDPOINT = 1;
	static const int CONFIG_MESSAGE_ENDPOINT = 2;
	static const int HOST_PACKET_ENDPOINT    = 3;
}

namespace LowsheenIPMessagePorts{
	static const int SCRUBBER_STATE_PORT = 81;
	static const int CONFIG_MESSAGE_PORT = 82;
	static const int HOST_PACKET_PORT    = 83;
}
/*
 The
 MessageChannel getDFUChannel() {
		return UsbMessageChannel(connection, LowsheenUsbEndPoint::DFU_ENDPOINT)
	}
 */

class BigChannelProvider:ChannelProvider{

	Udp connection;


	BigChannelProvider(Udp &udpConnection):connection(udpConnection){}
	MessageChannel getScrubberStateChannel() {
		return UsbMessageChannel(connection, LowsheenUsbEndPoint::SCRUBBER_STATE_ENDPOINT)
	}
	MessageChannel getConfigMessageChannel() {
		return UsbMessageChannel(connection, LowsheenUsbEndPoint::CONFIG_MESSAGE_ENDPOINT)
	}
	MessageChannel getHostPacketChannel() {
		return UsbMessageChannel(connection, LowsheenUsbEndPoint::HOST_PACKET_ENDPOINT)
	}
};

class LittleChannelProvider:ChannelProvider{
	MessageChannel getScrubberStateChannel() {
		return UdpMessageChannel(connection, LowsheenIPMessagePorts::SCRUBBER_STATE_PORT)
	}
	MessageChannel getConfigMessageChannel() {
		return UdpMessageChannel(connection, LowsheenIPMessagePorts::CONFIG_MESSAGE_PORT)
	}
	MessageChannel getHostPacketChannel() {
		return UdpMessageChannel(connection, LowsheenIPMessagePorts::HOST_PACKET_PORT)
	}

};



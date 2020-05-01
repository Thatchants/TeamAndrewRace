package packets;

public class PacketObstacle extends Packet{

	private String[] obstacleInfo;

	public PacketObstacle(String[] obstacleInfo) {
		this.obstacleInfo = new String[obstacleInfo.length];
		for(int i = 0; i < obstacleInfo.length;i++){
			this.obstacleInfo[i] = obstacleInfo[i];
		}
	}

	public PacketObstacle(byte[] data) {
		String[] dataElements = Packet.readData(data).split(",");
		this.obstacleInfo = new String[dataElements.length];
		for(int i = 0; i < dataElements.length;i++){
			this.obstacleInfo[i] = dataElements[i];
		}
	}

	public byte[] getData() {
		String byteString = "3";
		for(int i = 0; i < obstacleInfo.length;i++){
			byteString = byteString + obstacleInfo[i];
			if(i != obstacleInfo.length-1){
				byteString = byteString + ",";
			}
		}
		return (byteString).getBytes();
	}

	@Override
	public String getUsername() {
		return null;
	}

	public String[] getObstacleInfo(){
		return obstacleInfo;
	}
}

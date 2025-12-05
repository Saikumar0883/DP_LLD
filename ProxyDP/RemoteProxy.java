package ProxyDP;

interface IDataService {
    String fetchData();
}

class RealDataService implements IDataService {
    public RealDataService() {
        System.out.println("[RealDataService] Initialized (simulating remote setup)");
    }

    @Override
    public String fetchData() {
        return "[RealDataService] Data from server";
    }
}

class ProxyDataService implements IDataService {
    private RealDataService realDataService;

    public ProxyDataService() {
        realDataService = new RealDataService();
    }

    @Override
    public String fetchData() {
        System.out.println("[DataServiceProxy] Connecting to remote service...");
        return realDataService.fetchData();
    }
}

public class RemoteProxy {
    public static void main(String[] args) {
        IDataService dataService = new ProxyDataService();
        dataService.fetchData();
    }
}

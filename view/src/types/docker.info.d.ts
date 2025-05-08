export interface DockerSystemInfoResponse {
    success: boolean;
    message: string | null;
    errorCode: string | null;
    data: {
        version: DockerVersion;
        info: DockerInfo;
    };
}

// Docker 版本信息
export interface DockerVersion {
    ApiVersion: string; // API 版本，例如 "1.45"
    Arch: string; // 架构，例如 "amd64"
    GitCommit: string;
    GoVersion: string;
    KernelVersion: string;
    Os: string;
    Version: string; // Docker 引擎版本
    BuildTime: string;
    Experimental: boolean | null;
    MinAPIVersion: string;
    Platform: {
        Name: string; // 平台名称，例如 "Docker Engine - Community"
    };
    Components: DockerComponent[];
}

// Docker 引擎的各个组成部分
export interface DockerComponent {
    Name: string; // 组件名，例如 "Engine", "containerd", "runc"
    Version: string;
    Details: Record<string, string>; // 通常包含 GitCommit、BuildTime 等信息
}

// Docker 系统运行信息
export interface DockerInfo {
    neventsListener: number;
    ngoroutines: number;
    Architecture: string;
    Containers: number;
    ContainersStopped: number;
    ContainersPaused: number;
    ContainersRunning: number;
    CpuCfsPeriod: boolean;
    CpuCfsQuota: boolean;
    CPUShares: boolean;
    CPUSet: boolean;
    Debug: boolean;
    DiscoveryBackend: string | null;
    DockerRootDir: string;
    Driver: string;
    DriverStatus: string[][];
    SystemStatus: null; // 通常为 null，未来可定义
    Plugins: {
        Volume: string[];
        Network: string[];
        Authorization: string[] | null;
        Log: string[];
    };
    ExecutionDriver: string | null;
    LoggingDriver: string;
    ExperimentalBuild: boolean;
    HttpProxy: string;
    HttpsProxy: string;
    ID: string;
    IPv4Forwarding: boolean;
    BridgeNfIptables: boolean;
    BridgeNfIp6tables: boolean;
    Images: number;
    IndexServerAddress: string;
    InitPath: string | null;
    InitSha1: string | null;
    KernelVersion: string;
    Labels: string[];
    MemoryLimit: boolean;
    MemTotal: number; // 单位为 Byte
    Name: string;
    NCPU: number;
    NEventsListener: number;
    NFd: number;
    NGoroutines: number;
    NoProxy: string;
    OomKillDisable: boolean;
    OSType: string;
    OomScoreAdj: number | null;
    OperatingSystem: string;
    RegistryConfig: RegistryConfig;
    Sockets: null; // 一般为 null
    SwapLimit: boolean;
    SystemTime: string;
    ServerVersion: string;
    ClusterStore: string | null;
    ClusterAdvertise: string | null;
    Swarm: SwarmInfo;
    Isolation: string;
    SecurityOptions: string[];
    Runtimes: Record<string, {
        path: string;
        status: Record<string, string>;
    }>;
    CDISpecDirs: string[];
    RuncCommit: CommitInfo;
    OSVersion: string;
    LiveRestoreEnabled: boolean;
    InitCommit: CommitInfo;
    ContainerdCommit: CommitInfo;
    GenericResources: null;
    KernelMemoryTCP: boolean;
    DefaultRuntime: string;
    CgroupDriver: string;
    InitBinary: string;
    CgroupVersion: string;
    PidsLimit: boolean;
    Warnings: string[];
}

// 注册中心配置
export interface RegistryConfig {
    AllowNondistributableArtifactsCIDRs: string[] | null;
    AllowNondistributableArtifactsHostnames: string[] | null;
    InsecureRegistryCIDRs: string[];
    IndexConfigs: Record<string, {
        Name: string;
        Mirrors: string[];
        Secure: boolean;
        Official: boolean;
    }>;
    Mirrors: string[];
}

// Swarm 模式信息
export interface SwarmInfo {
    NodeID: string;
    NodeAddr: string;
    LocalNodeState: string;
    ControlAvailable: boolean;
    Error: string;
    RemoteManagers: null;
}

// 提交信息
export interface CommitInfo {
    ID: string;
    Expected: string;
}

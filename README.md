<h1 align="center">DuelDex</h1>

<p align="center">  
🎴DuelDex🎴 demonstrates modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, DataStore-Proto, Coil-Compose, Jetpack (Room, ViewModel, Navigation, Paging, ...), Convention Plugin, and Material Design based on MVVM architecture.
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/23bb7475-5c35-4879-984e-523788c4de99"/>
</p>

## Download
Go to the [Releases](https://github.com/roswkzm/DuelDex/releases) to download the latest APK.

<details>
<summary><h2>Use Tech Stack</h2></summary>

 - Minimun Sdk Level 26
 - 100% Kotlin & Jetpack Compose
 - Dependency Management
    - Version Catalogs
    - Convention Plugins
 - Dependency Injection
    - Hilt : Google의 Hilt 라이브러리는 Android 애플리케이션에서 종속성 주입(DI)을 간단하게 처리할 수 있다.
    - Hilt Navigation Compose : Hilt와 Jetpack Compose를 연동하기 위한 라이브러리로, 네비게이션 기능과 함께 사용할 수 있다.
 - Asynchronous Operations
    - Coroutines : 비동기 프로그래밍을 위한 Kotlin Coroutines 라이브러리
    - FLow : 비동기 데이터를 스트림 방식으로 처리
 - Networking
    - retrofit2 : Android에서 HTTP 요청을 보내고 API와 통신하기 위한 클라이언트 라이브러리
    - kotlinx-serialization-json : Kotlinx Serialization을 통해 JSON 데이터를 직렬화 및 역직렬화한다.
    - okhttp3 : Retrofit과 함께 사용되는 HTTP 클라이언트 라이브러리이며 logging-interceptor을 위해 사용한다.
    - sandwich : 네트워크 응답을 관리하고 처리하는 Skydoves(엄재웅)의 라이브러리로, Retrofit과 함께 사용됩니다.
 - Image Loading
     - Coil & Coil-Compose : Compose를 사용하여 이미지를 로드하고 표시하기 위한 Kotlin 기반의 라이브러리.
 - Jetpack Libraries 
     - Jetpack Compose : 선언적 UI 개발을 위한 Android의 최신 툴킷
     - Lifecycle : Android 수명 주기를 관찰하고 수명 주기 변경 시 UI 상태를 관리
     - ViewModel : UI 관련 데이터를 관리하고 수명주기를 인식하고 구성 변경을 통해 데이터 생존을 보장
     - Navigation : Hilt Navigation Compose와 함께 사용되어 화면 이동 구현
     - Hilt : Android 애플리케이션에서 종속성 주입(DI) 처리.
     - Room : Android의 SQLite 데이터베이스와 상호작용하는 라이브러리입니다. 데이터베이스를 손쉽게 관리 할 수 있다.
    - DataStore : SharedPreferences를 대체하는 데이터 저장소로, key-value 형식으로 데이터 저장 가능.
    - DataStore-Proto : 프로토콜 버퍼를 사용하여 데이터를 type-safety 하게 저장/관리 한다.
    - Paging-Compose : 대량의 데이터를 효율적으로 로드하고 화면에 표시할 수 있도록 돕는 기능을 제공
 - Other Libraries
    - timber : Android 애플리케이션에서 로깅을 위한 라이브러리
    - splashScreen : Android 12 이상에서 스플래시 화면을 구현하기 위한 라이브러리
    - vico : 차트를 그리기 위한 라이브러리입니다. Compose와 함께 사용
</details>

<details>
<summary><h2>Architecture overview</h2></summary>
 
 <p align="center">
    <img src="https://github.com/user-attachments/assets/f330e8d8-b09f-4b1c-9fb8-bd5f4d9eda36" width="600"/>
 </p>
<h3>DuelDex는 Android 권장 앱 Architecture를 따릅니다.</h3>
 
<h3>Unidirectional Data Flow (UDF)</h3>
<div align="start">
    <img src="https://github.com/user-attachments/assets/5fe8dfc4-b71b-4a89-ac1d-9830be329df9" width="200"/>
</div>
<ul>
    <li>상태 호이스팅(State Hoisting)이라고도 알려진 단방향 데이터 흐름(UDF)은 상태가 하강하고 이벤트가 상승하여 단방향 정보 스트림으로 이어지는 널리 사용되는 아키텍처 패턴입니다.</li>
    <li>Compose는 이 방법론을 사용하여 UI 상태 표현을 담당하는 컴포저블 또는 구성요소를 상태 저장 및 수정을 관리하는 구성요소와 분리합니다.</li>
</ul>
     
<h3>UI Layer</h3>
<div align="start">
    <img src="https://github.com/user-attachments/assets/608eba39-3c71-4a3c-9ff9-84bf022c2542" width="300"/>
</div>
<ul>
    <li>UI Elements: 화면에 데이터를 렌더링하는 UI 요소입니다. Jetpack Compose 기능을 사용하여 UI를 보여줍니다.</li>
    <li>State Holders: 데이터를 보유하고 이를 UI에 노출하며 논리를 처리하는 상태 홀더.</li>
</ul>
 
<h3>Domain Layer</h3>
<div align="start">
    <img src="https://github.com/user-attachments/assets/c19b6816-feb3-4c3f-af0d-72461c0adc64" width="300"/>
</div>
<ul>
    <li>도메인 레이어는 UI와 데이터 레이어 사이에 있는 선택적 레이어입니다.</li>
    <li>도메인 계층은 복잡한 비즈니스 논리 또는 여러 ViewModel에서 재사용되는 간단한 비즈니스 논리를 캡슐화하는 역할을 담당합니다.</li>
    <li>복잡성을 처리하거나 재사용성을 선호하는 등 필요한 경우에만 사용합니다.</li>
</ul>

<h3>Data Layer</h3>
<div align="start">
    <img src="https://github.com/user-attachments/assets/a0db434b-0b22-47ca-af13-d3d6180c32f9" width="300"/>
</div>
<ul>
    <li>앱의 데이터 계층에는 비즈니스 로직이 포함되어 있습니다.</li>
    <li>비즈니스 로직은 앱이 데이터를 생성, 저장 및 변경하는 방법을 결정하는 규칙으로 구성됩니다.</li>
</ul>

<h3>MVVM 디자인 패턴</h3>
<div align="start">
    <img src="https://github.com/user-attachments/assets/732ecdaf-09c7-4fe1-b9d7-67d86d72e65b" width="500"/>
</div>
<ul>
    <li>UI와 비즈니스 로직을 명확하게 분리하고, 테스트 가능성과 코드의 유지보수성을 향상시키는 아키텍처 패턴입니다.</li>
    <li>각 계층의 책임을 명확히 하고, State와 ViewModel을 통해 UI와 데이터의 상태를 효율적으로 동기화합니다.</li>
</ul>

<h3>Single Activity</h3>
<p>네비게이션 관리, 상태 관리, 코드 재사용성, 애니메이션 처리, 유지보수 측면에서 더 효율적이고 일관된 경험을 제공한다. 특히 Jetpack Navigation Component 및 Jetpack Compose와의 통합을 통해 최신 Android 애플리케이션에서 권장되는 아키텍처 패턴입니다.</p>

</details>

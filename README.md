<h1 align="center">DuelDex</h1>

<p align="center">  
🎴DuelDex🎴 demonstrates modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, DataStore-Proto, Coil-Compose, Jetpack (Room, ViewModel, Navigation, Paging, ...), Convention Plugin, and Material Design based on MVVM architecture.
</p>

<p align="center">
<img src="https://github.com/user-attachments/assets/23bb7475-5c35-4879-984e-523788c4de99"/>
</p>

## Download
Go to the [Releases](https://github.com/roswkzm/DuelDex/releases) to download the latest APK.

## 🚀 프로젝트 기획 의도
**DuelDex**는 유희왕 카드게임을 사랑하는 애호가들이 자신의 카드 컬렉션을 보다 쉽고 효율적으로 관리할 수 있도록 기획되었습니다. 이 앱을 통해 유저는 방대한 유희왕 카드 목록을 손쉽게 탐색하고, 원하는 카드를 빠르게 검색하며, 각 카드의 상세 정보를 직관적으로 확인할 수 있습니다.

또한, 다양한 카드 판매 사이트로 바로 연결되어 최적의 가격을 찾아볼 수 있으며, 가격 변동 추이를 그래프로 시각화하여 현명한 소비 결정을 도울 수 있습니다. 유저는 자신만의 플레이 스타일에 맞춘 커스텀 덱을 만들고, 다양한 카드 조합을 실험하며 전략을 세우는 재미를 극대화할 수 있습니다.

**DuelDex**는 단순한 카드 관리 앱을 넘어, 유저가 자신만의 덱을 창의적으로 구성하고 관리할 수 있는 기능을 통해 유희왕 카드게임의 전략적 즐거움을 더욱 깊이 있게 경험할 수 있도록 설계되었습니다. 이 앱은 유저의 카드게임 라이프를 한층 더 풍성하고 스마트하게 만들어 줄 것입니다.

<details>
<summary><h2>Use Tech Stack</h2></summary>

 - Minimun Sdk Level 26
 - 100% Kotlin & Jetpack Compose
 - Dependency Management
    - Version Catalogs
    - Convention Plugins
 - Dependency Injection
    - Hilt : Google의 Hilt 라이브러리는 Android 애플리케이션에서 종속성 주입(DI)을 쉽게 처리할 수 있다.
    - Hilt Navigation Compose : Hilt와 Jetpack Compose를 연동하기 위한 라이브러리로, 네비게이션 기능과 함께 사용할 수 있다.
 - Asynchronous Operations
    - Coroutines : 비동기 프로그래밍을 위한 Kotlin Coroutines 라이브러리
    - Flow : 데이터 스트림을 비동기적으로 처리
 - Networking
    - retrofit2 : Android에서 HTTP 요청을 보내고 API와 통신하기 위한 클라이언트 라이브러리
    - kotlinx-serialization-json : Kotlinx Serialization을 통해 JSON 데이터를 직렬화 및 역직렬화한다.
    - okhttp3 : Retrofit과 함께 사용되는 HTTP 클라이언트 라이브러리이며 logging-interceptor을 위해 사용한다.
    - sandwich : 네트워크 응답을 관리하고 처리하는 Skydoves(엄재웅)의 라이브러리로, Retrofit과 함께 사용됩니다.
 - Image Loading
     - Coil & Coil-Compose : Compose와 통합되어, Kotlin 기반으로 이미지를 쉽게 로드하고 표시할 수 있는 라이브러리
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
<summary><h2>Modularization</h2></summary>

<blockquote>
<details>
<summary><h2>Module화 장단점</h2></summary>

 - **👍장점👍**
    - **확장성(Scalability)**: 긴밀하게 결합된 코드베이스에서는 하나의 변경이 연쇄적인 수정 작업을 유발할 수 있습니다. 적절히 모듈화된 프로젝트는 관심사 분리(SOC) 원칙을 채택하여, 기여자들이 더 많은 자율성을 가지고 작업할 수 있게 하면서도 아키텍처 패턴을 강화합니다.
    - **병렬 작업 가능(Enabling work in parallel)**: 모듈화는 버전 관리 충돌을 줄이고, 큰 팀에서 개발자들이 병렬로 더 효율적으로 작업할 수 있게 도와줍니다.
    - **소유권(Ownership)**: 모듈은 전담 소유자가 있을 수 있으며, 이 소유자는 코드 및 테스트를 유지 관리하고, 버그를 수정하며, 변경 사항을 검토할 책임이 있습니다.
    - **캡슐화(Encapsulation)**: 코드가 분리되어 있으면 읽기, 이해, 테스트, 유지 관리가 더 쉬워집니다.
    - **빌드 시간 감소(Reduced build time)**: Gradle의 병렬 및 증분 빌드를 활용하면 빌드 시간을 줄일 수 있습니다.
    - **재사용성(Reusability)**: 적절한 모듈화는 코드 공유와 여러 앱(또는 플랫폼 간)에서 동일한 기반을 활용하여 앱을 빌드할 수 있는 기회를 제공합니다.

 - **👎단점👎**
    - **너무 많은 모듈**: 각 모듈은 빌드 구성의 복잡성 증가로 인해 오버헤드를 발생시킵니다. 이는 Gradle 동기화 시간을 늘릴 수 있으며, 지속적인 유지 관리 비용이 발생합니다. 또한, 모듈이 많아질수록 단일 모듈로 구성된 프로젝트보다 Gradle 설정의 복잡성이 증가합니다. 
    - **모듈화가 부족할 경우**: 반대로 모듈이 너무 적고 크거나, 서로 긴밀하게 결합되어 있으면 또 다른 모놀리식(단일화) 구조로 끝날 수 있습니다. 이는 모듈화의 이점 중 일부를 잃게 되는 것을 의미합니다. 모듈이 과도하게 비대하고 명확한 목적이 정의되지 않았다면, 모듈을 분리하는 것을 고려해야 합니다.
    - **너무 복잡함**: 여기에는 완벽한 해결책이 없습니다. 실제로 모든 프로젝트에서 모듈화를 하는 것이 항상 합리적인 것은 아닙니다. 중요한 요소는 코드베이스의 크기와 상대적인 복잡성입니다. 프로젝트가 일정 수준 이상 성장할 것으로 예상되지 않는다면, 모듈화로 얻는 확장성이나 빌드 시간 단축의 이점이 적용되지 않을 수 있습니다.
    
</details>
</blockquote>

<h2 align="center">DuelDex Modularization</h2>

<img src="https://github.com/user-attachments/assets/21bc17a2-2b51-4b15-877b-d302c504552e" width="300"/>

 - **App Module**: 이 모듈은 앱 수준의 클래스와 프로젝트의 나머지 코드베이스를 결합하는 기본 클래스들을 포함한다. 예를 들어 MainActivity, Scaffold, AppState, 그리고 앱 수준에서 제어되는 네비게이션 등이 있습니다. NavHost를 통해 앱 내 네비게이션을 일관되게 처리하고, TopLevelDestination을 통한 하단 네비게이션 바를 간단하게 설정할 수 있습니다. app 모듈은 모든 feature 모듈과 필요한 core 모듈에 의존합니다.

 - **Feature Module**: 앱 내에서 단일 책임을 처리하는 기능별 모듈입니다. 이러한 모듈은 필요할 때 다른 앱이나 테스트, 또는 다른 flavor 앱에서도 재사용할 수 있지만, 여전히 독립적으로 분리되고 격리됩니다. 특정 Feature 모듈에서만 필요한 클래스는 해당 모듈 내에 남아 있어야 하며, 그렇지 않은 경우 적절한 core 모듈로 추출해야 합니다. Feature 모듈은 필요한 경우 Core 모듈과만 상호작용하며, 다른 Feature 모듈에 의존하지 않습니다.
     - **일반적으로 각 기능별 UI와 다른 Module에서 데이터를 읽는 UI 구성요소와 ViewModel을 포함한다.**
 
 - **Core Module**: 앱 내의 다른 모듈 간에 공유되어야 하는 보조 코드와 특정 의존성을 포함하는 공통 라이브러리 모듈입니다. 이 모듈들은 다른 core 모듈에 의존할 수 있지만, feature 모듈이나 app 모듈에는 의존해서는 안 됩니다.
    - **core:common**: 공통적으로 사용되는 유틸리티 클래스나 함수들을 제공하는 모듈입니다. 앱 전반에서 재사용 가능한 코드를 포함하고 모든 모듈에서 접근할 수 있습니다.
    - **core:data**: 데이터와 관련된 모든 처리를 담당하는 모듈입니다. 원격 또는 로컬 데이터 소스에서 데이터를 가져오고 Repository 패턴을 구현하여 데이터를 관리합니다.
    - **core:database**: Local Database를 관리하는 모듈로, Room을 통해 데이터 저장, 검색, 수정, 삭제 등을 수행합니다.
    - **core:datastore**: 앱의 설정 값이나 간단한 사용자 데이터를 저장하고 관리하는 모듈입니다. 키-값 쌍으로 데이터를 저장합니다.
    - **core:datastore-proto**: DataStore의 프로토콜 버퍼 버전을 사용하는 모듈로, 구조화된 데이터를 직렬화하여 저장하고 관리합니다. 더 복잡한 데이터 구조를 안전하게 저장할 때 사용됩니다.
    - **core:designsystem**: 앱의 핵심 UI 구성 요소를 관리하는 모듈입니다. 버튼, 색상, 폰트, 테마 등과 같은 디자인 요소들을 일관성 있게 유지하기 위해 사용되며, 디자인 시스템의 컴포넌트를 중앙에서 관리합니다.
    - **core:domain**: 비즈니스 로직을 담당하는 모듈입니다. 비즈니스 규칙을 정의하며, 데이터를 처리한 후 UI로 전달하기 전 단계에서 중요한 역할을 합니다.
    - **core:model**: 앱 전체에서 사용되는 모델 클래스입니다.
    - **core:network**: 네트워크 통신과 관련된 모듈입니다. API 호출, 네트워크 요청/응답 처리와 같은 로직을 포함하며, 원격 서버와의 데이터를 주고받는 역할을 합니다.
    - **core:ui**: 앱 전반에 공통적으로 사용되는 복합 UI 구성 요소를 관리하는 모듈입니다. 앱의 여러 부분에서 재사용될 수 있는 공통 UI 요소들이 포함되어 있습니다.

 - **Build-Logic Module**: 앱 내에서 모듈들의 Build와 관련된 공통 로직을 관리한다. Convention Plugin을 사용해 프로젝트 전반에 적용되는 빌드 규칙이나 스타일 가이드, 코드 품질 규칙 등을 정의할 수 있습니다. 이를 통해 코드 일관성을 유지하면서 여러 모듈에 적용할 수 있습니다.

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
    <li>상태 호이스팅(State Hoisting)이라고도 불리는 단방향 데이터 흐름(UDF)은 상태가 하향 전달되고, 이벤트가 상향 전달되는 패턴으로, 단일 방향으로 정보가 흐릅니다.</li>
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

<details>
<summary><h2>동작 화면</h2></summary>
 
### Home Screen
<div>
  <video controls width="300" src="https://github.com/user-attachments/assets/0fb09735-bf32-41f5-b937-f7697c7774f3"></video>
</div>

 - **Paging-Compose 사용**: 대량의 데이터를 페이징 처리하여 효율적으로 로드 및 표시
     - **pagingItems의 loadState에 따른 대응**: 데이터 로드 상태에 따라 적절한 UI를 제공
         - **Loading 상태**: 데이터 로딩 중일 때 Skeleton UI를 표시하여 사용자 경험을 개선
         - **Error 상태**: 데이터 로드 중 에러가 발생하면 에러 메시지와 재시도 버튼을 제공하여 사용자에게 명확한 피드백 제공

### Search Screen
<div>
 <video controls src="https://github.com/user-attachments/assets/9e270752-da7c-42a7-a98f-e022e9504364" width="300"></video>
</div>

 - **검색어가 입력될 때마다 실시간으로 검색 API 요청 발생**
     - **Debounce 적용 (500ms)**: 500ms 동안 입력이 중지될 때까지 대기 후, 마지막 검색어로 한 번만 검색 API 요청
         - **서버 부하 감소**: 불필요한 중복 요청을 막아 서버에 가해지는 부하를 줄임
         - **네트워크 사용량 감소**: 사용자의 네트워크 자원을 절약하고, 요청 빈도를 줄여 네트워크 트래픽을 최적화
         - **성능 최적화**: 빠르고 정확한 응답을 통해 검색 기능의 전체적인 성능을 개선, 사용자 경험 향상
 - **DataStore 및 DataStore-Proto 사용**: DataStore-Proto는 구조화된 데이터를 직렬화하여 타입 안전성을 보장하며, 사용자 데이터를 안전하게 저장하고 관리한다.
     - **최근 검색어 기능 구현**: 사용자의 최근 검색어를 저장하고 앱 재실행 시에도 유지
         - **DataStore 사용**: 간단한 key-value 방식으로 최근 검색어를 저장 및 불러오기
         - **Proto 사용**: 구조화된 데이터를 저장하여 타입 안전성을 제공, 데이터 타입을 컴파일 시점에 검증해 안정적인 데이터 처리를 보장
         - **사용자 경험 개선**: 최근 검색어를 빠르게 제공하여 검색 편의성 향상
 - **검색 필터 기능 구현**: 검색 결과를 다양한 기준에 따라 필터링
     - **Enum 사용**: 필터링 기준을 Enum으로 정의하여 코드 가독성 및 유지보수성을 향상
         - 각 필터 옵션을 Enum 값으로 관리하여 타입 안정성을 제공하고, 잘못된 값 사용을 방지
         - 새로운 필터 조건 추가 시 Enum에 값만 추가하면 쉽게 확장 가능
         - 다양한 필터 옵션을 제공하여 원하는 검색 결과를 빠르게 찾을 수 있도록 지원
 - **추천 검색어 구현**

### Detail Screen
<div>
 <video controls src="https://github.com/user-attachments/assets/b590210d-3fc5-4a23-8b21-3644efe202c8" width="300"></video>
</div>

 - **CarouselPager 구현**: HorizontalPager의 graphicsLayer와 Offset 값을 사용하여 카드 이미지를 자연스럽게 좌우로 넘기는 캐러셀(Pager) 기능 구현
     - **graphicsLayer**: 이미지에 다양한 시각적 효과를 주기 위해 사용
     - **Offset 값**: 페이지 전환 시 위치를 조정하여 부드러운 전환 애니메이션 구현
     - **사용자 경험 개선**: 카드 이미지를 직관적이고 세련된 방식으로 탐색할 수 있도록 지원
 - **카드를 Deck에 추가하는 기능 구현**: 카드 상세화면에서 사용자가 카드를 원하는 덱에 바로 추가 가능
    - **AlertDialog 사용**: 현재 카드의 추가를 위해 덱 목록을 AlertDialog로 보여줌
         - 사용자는 목록에서 덱을 선택하여 카드를 손쉽게 추가 가능
         - 빠르고 직관적인 덱 관리 기능을 통해 카드 구성의 편의성 향상
 - **카드 판매 사이트 이동 기능 구현**: 사용자가 카드 판매 사이트로 쉽게 접근할 수 있도록 WebView를 통해 외부 사이트로 이동
 - **카드 가격 정보 Chart 구현**: 해당 카드의 가격 변동을 시각적으로 확인 가능
    - **Chart 사용**: 카드의 가격 정보를 그래프로 표시하여 과거 및 현재 가격 변동을 한눈에 파악
    - **사용자 경험 개선**: 가격 정보를 직관적으로 제공하여 사용자의 구매 결정을 지원

### Deck Screen
<div>
 <video controls src="https://github.com/user-attachments/assets/f73493da-dbf9-4dfa-8ad4-ebd8b7b5567b" width="300"></video>
</div>

 - **Card Deck 화면 구현**: 사용자가 Deck을 관리할 수 있는 화면 제공
 - **Room DB 사용**: 로컬 데이터베이스를 통해 Deck 관련 CRUD(생성, 읽기, 업데이트, 삭제) 기능 구현
 - **Card-Deck 관계형 DB 구현**: Deck 내에서 카드를 효율적으로 관리하기 위한 데이터베이스 구조
    - **CardId와 DeckId를 통한 관계 설정**: 각 카드를 특정 Deck에 추가하거나 관리할 수 있도록 CardId와 DeckId를 기반으로 관계형 DB를 생성
        - **Deck 내 카드 추가/삭제**: 특정 Deck에 카드를 추가하거나 삭제하여 관리
        - **카드-Deck 관계 조회**: 특정 Deck에 속한 카드 목록을 불러오거나, 카드가 속한 Deck을 조회
    - 사용자가 다양한 덱을 구성하고 관리할 수 있도록 효율적인 카드 관리 시스템 제공
 - **Deck 내 카드를 다양한 방식으로 볼 수 있도록 두 가지 뷰 옵션 제공**
     - **GridCard**: 카드를 그리드 형식으로 표시하여 한눈에 여러 카드를 확인할 수 있는 레이아웃
     - **ListCard**: 카드를 리스트 형식으로 표시하여 카드의 세부 정보를 쉽게 확인할 수 있는 레이아웃
     - 사용자가 선호하는 방식으로 카드를 볼 수 있도록 선택지 제공, 더 나은 탐색 경험 제공


### Config Change
<div>
 <video controls src="https://github.com/user-attachments/assets/476030c6-2949-49bc-885c-206556f5f94a" width="300"></video>
</div>

 - **Config Change 기능 구현**: 앱의 설정 변경에 따라 즉시 반응하는 UI 제공
    - **Dark Mode Theme 지원**: 어두운 테마를 지원하여 사용자의 선호에 맞는 화면 제공
         - **FollowSystem**, **Light**, **Dark** 모드 지원
    - **Localization 설정 기능 구현**: 앱의 로케일을 사용자가 설정할 수 있는 기능 제공
        - **FollowSystem**, **English**, **Korean** 언어 지원
 - **사용자 경험 개선**: 다양한 테마 및 언어 옵션을 제공하여 사용자가 자신에게 맞는 환경을 선택할 수 있도록 지원

### Network Monitoring
<div>
 <video controls src="https://github.com/user-attachments/assets/8d7d1a8d-6dc7-44fe-93fe-c195318e4d25" width="300"></video>
</div>

 - **Network Monitoring 기능 구현**: 네트워크 상태를 실시간으로 감지하여 사용자에게 알림 제공
     - **네트워크 끊김 감지**: 네트워크 연결이 끊겼을 때 자동으로 감지
     - **SnackBar 알림 제공**: 네트워크가 끊길 경우 사용자에게 SnackBar를 통해 실시간 알림 제공

### Skeleton Loading
<img src="https://github.com/user-attachments/assets/c471096b-22fa-4823-a1f2-56158e81e79e" width="300"/>

 - **Skeleton Loading 사용**: 통신 시간이 긴 화면에서 사용자들이 기다리는 동안 빈 화면을 보여주는 대신 Skeleton UI로 대체
     - **체감 대기 시간 감소**: Skeleton Loading을 통해 통신 완료 전까지 화면을 미리 로드한 것처럼 보여주어 사용자들의 기다리는 체감 시간을 줄임
     - **사용자 경험 개선**: 더 나은 시각적 피드백을 제공하여 사용자 만족도 향상

</details>


<details>
<summary><h2>프로젝트 회고</h2></summary>

DuelDex 프로젝트는 Android 최신 기술 스택을 활용하여 카드를 관리하고 사용자 경험을 극대화하는 데 중점을 두었습니다. Jetpack Compose, Hilt, Coroutines 등과 함께 모듈화된 아키텍처를 적용하여 앱의 구조적 확장성과 사용자 편의성을 고려한 설계가 주요 목표였습니다. 특히 이번 프로젝트는 1인 개발로 진행되었으며, 모든 기능을 처음부터 끝까지 스스로 설계하고 구현했다는 점에서 더욱 의미 있는 도전이었습니다.
 
  - **Jetpack Compose의 심화 적용**: Jetpack Compose는 실무 프로젝트에서도 사용해 본 기술이지만, 이번 프로젝트에서는 더 심화된 기능들을 다루며, 복잡한 UI를 더 효율적으로 관리하는 방법을 탐구했습니다. 특히, graphicsLayer와 offset과 같은 고급 기능을 활용하여 유저들이 카드 이미지를 직관적으로 탐색할 수 있도록 구현한 부분이 큰 성과였습니다. 단순한 컴포저블 함수 작성에서 더 나아가, 애니메이션과 상태 관리 등 고급 기능을 효과적으로 적용하며 Jetpack Compose의 장점을 깊이 이해할 수 있었습니다.

 - **모듈화의 실질적인 개선**: 이번 프로젝트에서는 기존보다 더 세분화된 모듈화를 시도했습니다. 특히, Core 모듈과 Feature 모듈을 적절히 분리함으로써 유지보수성과 확장성을 크게 향상시켰습니다. 코드 관리와 유지보수를 철저히 고려하였으며, 코드 재사용성과 병렬 작업의 이점을 최대한 활용했습니다. UI와 데이터 처리 로직을 깔끔하게 분리하여 새 기능을 추가하거나 유지보수할 때, 다른 기능에 영향을 주지 않고 독립적으로 작업할 수 있었습니다. 

 - **사용자 경험(UX) 강화**: 이번 프로젝트에서 사용자 경험을 개선하는 데 많은 시간을 할애했습니다. Skeleton Loading, Dark Mode 지원, 그리고 실시간 검색 기능 등을 통해 유저들이 앱을 더 직관적이고 편리하게 사용할 수 있도록 고민했습니다. 특히, 가격 변동 차트와 Deck 관리 기능을 통해 유저들이 카드를 효율적으로 관리하고 게임을 준비하는 데 도움이 되는 기능들을 구현한 점이 인상적이었습니다. 이러한 세부적인 기능들이 앱의 전체적인 사용자 경험을 크게 향상시키는 데 기여했습니다.

 - **부족했던 점**: 이번 프로젝트는 기술적으로 만족스러웠지만, 몇 가지 아쉬운 점도 있었습니다.
     - **테스트 코드 부족**: 앱 기능 구현에 집중하다 보니 유닛 테스트나 UI 테스트 작성에 시간을 투자하지 못한 것이 아쉬웠습니다. 이전 실무 프로젝트를 통해 테스트 코드의 중요성을 몸소 느낀 만큼, 차후에는 더 체계적으로 테스트를 작성하고 코드의 신뢰성을 높여야겠다고 생각했습니다.

     - **디자인 일관성**: 디자이너 없이 1인 개발을 하다 보니, 초반부터 컴포넌트나 색상 설계를 충분히 고려하지 못해 DesignSystem 모듈의 이점을 크게 살리지 못한 부분이 아쉬웠습니다. 디자인 시스템을 좀 더 명확하게 설정해두었다면, 전체적으로 일관성이 있고 더 완성도 높은 결과물이 되었을 것 같습니다.
     
     - **앱 성능 측정 미흡**: 이번 프로젝트에서는 benchmark와 같은 앱 성능 측정 도구를 활용하지 못한 것이 아쉬웠습니다. 앱 성능을 더 정확히 평가하고 최적화할 수 있었던 중요한 기회를 놓친 것이 아쉬움으로 남았습니다. 추후 더 공부한 후에 성능 측정을 추가하여 프로젝트의 완성도를 높일 계획입니다.

 위에서 언급한 아쉬운 점들은 이번 DuelDex 프로젝트를 통해 중요한 교훈으로 남았습니다. 향후 프로젝트에서는 더 철저한 계획 수립과 최적화, 디자인 일관성을 유지하여 보다 완성도 높은 결과물을 만들어 나갈 것입니다.
</details>



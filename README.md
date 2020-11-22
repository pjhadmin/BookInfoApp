
# Book Info App

## 1. 기능설명
Daum Book API를 통해 제목으로 책을 검색할 수 있습니다

검색 시 검색 키워드의 입력에 따른 결과를 노출합니다

검색결과가 많을 경우 스크롤을 내릴때마다 추가 데이터를 가져오며

책 상세 정보의 좋아요를 누르면 앞서 검색했던 리스트에 좋아요 표시가 반영됩니다

## 2. Proejct 수행전략
2.1 Architecture

MVVM ( Model View ViewModel )

![image](https://user-images.githubusercontent.com/17893308/93020802-45de4a80-f61a-11ea-8cb3-f61611c6d01d.png)

2.2 Library

- JetPack
    - ViewModel, DataBinding, LiveData, Paging, AppCompat, ~~navigation~~
- DI
    - Hilt
- Thread
    - Coroutine
- Network
    - Retrofit2
- Image
    - Glide

## 3. API
[https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-book](https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-book)

## 4. 참고 사이트
[https://github.com/android10/Android-CleanArchitecture/tree/master/data/src/main](https://github.com/android10/Android-CleanArchitecture/tree/master/data/src/main)

[https://developer.android.com/training/dependency-injection/hilt-android?hl=ko](https://developer.android.com/training/dependency-injection/hilt-android?hl=ko)

[https://developer.android.com/topic/libraries/architecture/paging?hl=ko](https://developer.android.com/topic/libraries/architecture/paging?hl=ko)

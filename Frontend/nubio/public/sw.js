self.addEventListener('fetch', function(e) {
    // 요청의 Accept 헤더에서 이미지 형식 확인
    const acceptedTypes = ['image/avif', 'image/webp', 'image/apng', 'image/svg+xml', 'image/*'];
  
    if (acceptedTypes.some(type => e.request.headers.get('Accept').includes(type))) {
      e.respondWith(
        caches.match(e.request).then(function(r) {
          console.log('[Service Worker] Fetching resource: ' + e.request.url);
          return r || fetch(e.request).then(function(response) {
            return caches.open('danbi').then(function(cache) {
              console.log('[Service Worker] Caching new resource: ' + e.request.url);
              cache.put(e.request, response.clone());
              return response;
            });
          });
        })
      );
    } else {
      // 지정한 이미지 형식이 아닌 경우, 네트워크에서 직접 요청 처리
      e.respondWith(fetch(e.request));
    }
});

self.addEventListener('install', function(e) {
    console.log('[Service Worker] Install');
    e.waitUntil(
      caches.open('danbi').then(function(cache) {
        return cache.addAll([
          '/',
          '/index.html',
          '/src/'
        ]);
      })
    );
});
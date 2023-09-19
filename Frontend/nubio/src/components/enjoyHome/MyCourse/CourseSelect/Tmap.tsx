import { useEffect, useState } from 'react';

const { Tmapv2 } = window as any; 

declare global {
    interface Window {
      Tmapv2: any;
    }
  }
  
const Tmap = () => {
    const dummy1 = process.env.PUBLIC_URL + '/assets/dummy/dummy1.jpg';
  const dummyUrl = process.env.PUBLIC_URL + '/assets/dummy/dummy2.jpg';
    var positions = [
        {
          title: '카카오',
          lat: 33.450705,
          lng: 126.570677,
          img_url: dummy1,
          category_group_code: 'CT1'
        },
        {
          title: '생태연못',
          lat: 33.450936,
          lng: 126.569477,
          img_url: dummyUrl,
          category_group_code: 'CE7'
        },
        {
          title: '텃밭',
          lat: 33.450879,
          lng: 126.56994,
          img_url: dummyUrl,
          category_group_code: 'FD6'
        },
        {
          title: '근린공원',
          lat: 33.451393,
          lng: 126.570738,
          img_url: dummyUrl,
          category_group_code: 'AT4'
        },
      ];

    const category: { [key: string]: string } = {
        'CT1': process.env.PUBLIC_URL + '/assets/marker/movieM.png',
        'CE7': process.env.PUBLIC_URL + '/assets/marker/cafeM.png',
        'FD6': process.env.PUBLIC_URL + '/assets/marker/eatM.png',
        'AT4': process.env.PUBLIC_URL + '/assets/marker/walkM.png',
    }
    const [map, setMap] = useState<any>(null);
    const [manager, setManager] = useState<any>(null);
    const CURRENT_MAP = () => {
        const mapI = new Tmapv2.Map('map_id', {
            center: new Tmapv2.LatLng(37.5, 126.9), // 지도 초기 좌표
            width: '430px',
            height: '932px',
            zoom: 8
        });
        setMap(mapI);
        positions.map((pos) => {
            const iconUrl = category[pos.category_group_code];
            const marker = new Tmapv2.Marker({
                position: new Tmapv2.LatLng(pos['lat'], pos['lng']),
                map: mapI,
                iconSize: new Tmapv2.Size(50, 50),
                icon: iconUrl,
            })
        })
        // 도형을 그리는 객체 생성
        const drawingObj = new Tmapv2.extension.Drawing(
            {
                map:mapI, // 지도 객체
                strokeWeight: 4, // 테두리 두께
                strokeColor:"blue", // 테두리 색상
                strokeOpacity:1, // 테두리 투명도
                fillColor:"red", // 도형 내부 색상
                fillOpacity:0.2 // 도형 내부 투명도
            }
        ); 
        drawingObj.addListener('touchend', () => {
            console.log('dblClick')
            // drawingObj.isDrawing = false; // 그리기 모드 종료
        });
        console.log(drawingObj)
        setManager(drawingObj);
    }
	
	function drawPolyline() {
        manager.drawPolyline();
    }
    
	function clearDrawing() {
		// 도형 객체의 도형을 삭제하는 함수
		manager.clear();
	}

    useEffect(() => {
        CURRENT_MAP();
    }, [])
    return(
        <div id='map_id'>
            <div className="map_act_btn_wrap clear_box" style={{position: 'absolute', zIndex: '1', paddingLeft: '10px'}}>
                <button onClick={() => drawPolyline()}>라인 그리기</button>
                <button onClick={() => clearDrawing()}><b>라인 삭제하기</b></button>
            </div>
        </div>
    )
}

export default Tmap;
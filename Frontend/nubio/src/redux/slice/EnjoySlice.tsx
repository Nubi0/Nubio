import { PayloadAction, createSlice } from '@reduxjs/toolkit';

const EnjoySlice = createSlice({
  name: 'enjoyslice',
  initialState: {
    manager: null,
    time: null,
    positions: [] as any[],
    location: null,
    course_tag: [] as string[],
    courseList: [],
    coursePoint: [],
    region: null,
  },
  reducers: {
    setManager: (state, action) => {
      state.manager = action.payload;
    },
    setTime: (state, action) => {
      state.time = action.payload;
    },
    setLocation: (state, action) => {
      state.location = action.payload;
    },
    setPosition: (state, action) => {
      const newPosition = action.payload; // 새로운 위치
    
      // 중복된 요소가 있는지 확인
      const isDuplicate = state.positions.some((position) => {
        // 여기서 index가 같은 요소를 중복으로 판단
        return position.place_name === newPosition.place_name;
      });
    
      if (!isDuplicate) {
        // 중복된 요소가 없을 경우, 새로운 위치를 추가
        newPosition.index = state.positions.length + 1; // 다음 index 계산
    
        // 재정렬된 인덱스를 적용하기 위한 작업 시작
        const updatedPositions = state.positions.map((position) => {
          if (position.index >= newPosition.index) {
            // 현재 인덱스가 새로운 위치 이후에 있는 경우 인덱스를 하나씩 증가
            position.index += 1;
          }
          return position;
        });
    
        return {
          ...state,
          positions: [...updatedPositions, newPosition],
        };
      } else {
        const filteredPositions = state.positions.filter((el) => el.place_name !== newPosition.place_name)
        return {
          ...state,
          positions: filteredPositions,
        };
      }
    },
    setTag: (state, action: PayloadAction<string>) => {
      const name = action.payload;
      const exists = state.course_tag.includes(name);
      if (exists) {
        // 이미 존재하는 경우, 배열에서 제거
        state.course_tag = state.course_tag.filter((el) => el !== name);
      } else {
        // 존재하지 않는 경우, 추가
        state.course_tag.push(name);
      }
    },
    setCourseList: (state, action) => {
      state.courseList = action.payload;
    },
    setCoursePoint: (state, action) => {
      state.coursePoint = action.payload;
    },
    resetPosition: (state) => {
      state.positions = [];
    },
    setRegion: (state, action) => {
      state.region = action.payload;
    }
  },
});

export const { setManager, setTime, setPosition, setTag, setCourseList, setLocation, setCoursePoint, resetPosition, setRegion } = EnjoySlice.actions;
export default EnjoySlice.reducer;

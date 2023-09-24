import { PayloadAction, createSlice } from '@reduxjs/toolkit';

const EnjoySlice = createSlice({
  name: 'enjoyslice',
  initialState: {
    manager: null,
    time: null,
    positions: [],
    tag: [] as string[],
    courseList: []
  },
  reducers: {
    setManager: (state, action) => {
      state.manager = action.payload;
    },
    setTime: (state, action) => {
      state.time = action.payload;
    },
    setPosition: (state, action) => {
      state.positions = action.payload;
    },
    setTag: (state, action: PayloadAction<string>) => {
      const name = action.payload;
      const exists = state.tag.includes(name);
      if (exists) {
        // 이미 존재하는 경우, 배열에서 제거
        state.tag = state.tag.filter((el) => el !== name);
      } else {
        // 존재하지 않는 경우, 추가
        state.tag.push(name);
      }
    },
    setCourseList: (state, action) => {
      state.courseList = action.payload;
    }
  },
});

export const { setManager, setTime, setPosition, setTag, setCourseList } = EnjoySlice.actions;
export default EnjoySlice.reducer;

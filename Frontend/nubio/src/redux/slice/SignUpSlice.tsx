import { createSlice } from '@reduxjs/toolkit';

const SignUpSlice = createSlice({
  name: 'signupSlice',
  initialState: {
    taste: [
      {
        type: 'drink',
        detailTypes: [] as string[],
      },
      {
        type: 'food',
        detailTypes: [] as string[],
      },
      {
        type: 'play',
        detailTypes: [] as string[],
      },
    ],
  },
  reducers: {
    setTaste: (state, action) => {
      // action.payload에는 선택한 이미지의 이름과 타입이 포함되어야 합니다.
      const { name, type } = action.payload;

      // 이미지가 이미 선택되어 있는지 확인
      const existingIndex = state.taste.findIndex(
        (item) => item.type === type && item.detailTypes.includes(name)
      );

      if (existingIndex === -1) {
        // 이미지가 선택되어 있지 않은 경우 추가
        state.taste.find((item) => item.type === type)?.detailTypes.push(name);
      } else {
        // 이미지가 이미 선택된 경우 제거
        state.taste.find((item) => item.type === type)?.detailTypes.splice(
          state.taste[existingIndex].detailTypes.indexOf(name),
          1
        );
      }
    },
  },
});

export const { setTaste } = SignUpSlice.actions;
export default SignUpSlice.reducer;

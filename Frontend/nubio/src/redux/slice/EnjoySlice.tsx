import { createSlice } from '@reduxjs/toolkit';

const EnjoySlice = createSlice({
  name: 'enjoyslice',
  initialState: {
    manager: null,
    time: null,
    positions: [],
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
  },
});

export const { setManager, setTime, setPosition } = EnjoySlice.actions;
export default EnjoySlice.reducer;

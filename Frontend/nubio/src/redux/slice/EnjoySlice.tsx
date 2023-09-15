import { createSlice } from "@reduxjs/toolkit";

const EnjoySlice = createSlice({
    name: 'enjoyslice',
    initialState: {
        manager: null 
    },
    reducers: {
        setMap: (state, action) => {
            state.manager = action.payload;
        }
    }
});

export const { setMap } = EnjoySlice.actions;
export default EnjoySlice.reducer;

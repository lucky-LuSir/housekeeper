<template>
    <div>
        <city-picker :pick="picker" @selectProvice="selectPicker"></city-picker>

        {{picker.provinceCode}};{{picker.cityCode}};{{picker.regionCode}};{{picker.streetCode}};
        {{picker.provinceName}};{{picker.cityName}};{{picker.regionName}};{{picker.streetName}};
    </div>
</template>

<script>
    import cityPicker from '../components/cityPicker';

    export default {
        components: {
            cityPicker
        },
        data() {
            return {
                province:[],
                city: [],
                region: [],
                street: [],
                picker:{
                    provinceName: "湖北",
                    cityName: "武汉",
                    regionName: "江夏",
                    streetName: "高新四路",
                    provinceCode: "100",
                    cityCode: "11000",
                    regionCode: "110000",
                    streetCode: "1515656"
                }
            };
        },
        methods:{
            selectPicker(picker){
                this.picker.provinceName = picker.provinceName;
                this.picker.cityName = picker.cityName;
                this.picker.regionName = picker.regionName;
                this.picker.streetName = picker.streetName;
                this.picker.provinceCode = picker.provinceCode;
                this.picker.cityCode = picker.cityCode;
                this.picker.regionCode = picker.regionCode;
                this.picker.streetCode = picker.streetCode;
            }
        }
    }
</script>

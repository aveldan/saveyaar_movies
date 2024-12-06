import React from "react";
import { Image, StyleSheet, View } from "react-native";
import { StarIcon } from "@/assets/svg";
import Scale from "@/constants/Scale";
import TextComponent from "./TextComponents";

interface input{
    rating: number
};

export default function RatingComponent({rating}: input) {
    return(
        <View style={styles.container}>
            <StarIcon width={20} height={20}/>
            <TextComponent
                text={rating.toString()}
                numberOfLine = {1}
                size={11}
            />
        </View>
    )
};

const styles = StyleSheet.create({
    container: {
        width: Scale(50),
        height: Scale(23),
        flexDirection: 'row',
        backgroundColor: "#333333",
        alignItems: 'center',
        borderRadius: 50,
        paddingHorizontal: 3,
    },
});
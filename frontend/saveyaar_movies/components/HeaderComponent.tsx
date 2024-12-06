import React from "react";
import { useRouter } from "expo-router";
import { View, StyleSheet, TouchableOpacity } from "react-native";

import Scale from "@/constants/Scale";
import { BackArrow } from "@/assets/svg";
import TextComponent from "./TextComponents";
import { EditIcon } from "@/assets/svg";

interface Props{
    title: string,
    edit?: Boolean
};

export default function HeaderComponent({title, edit = false}: Props) {

    const router = useRouter();

    const handleBack = () => {
        router.back();
    }

    return (
        <View style={styles.header}>
            <TouchableOpacity onPress={handleBack}>
                <BackArrow width={30.89} height={14.81} />
            </TouchableOpacity>
            <View style={styles.titleStyle}>
                <TextComponent text={title} size={22} color="#FFFFFF"/>
            </View>
            {edit && 
                <TouchableOpacity>
                    <EditIcon width={24} height={24} />
                </TouchableOpacity>
            }
        </View>
    )
};


const styles = StyleSheet.create({
    header: {
      flexDirection: 'row',
      marginTop: Scale(62),
      alignItems: 'center',
      justifyContent: 'space-between',
      marginHorizontal: Scale(18),
    },
    iconStyle: {
      height: Scale(24),
      width: Scale(24),
    },
    iconConatainer: {
      flexDirection: 'row',
      flex: 0.2,
      justifyContent: 'flex-end',
      gap: Scale(8),
    },
    titleStyle: {
        flex: 1, 
        alignItems: 'center',
    },
  });
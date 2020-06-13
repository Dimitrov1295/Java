package uk.co.ivandimitrov.rest.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * A class used to contain the database results.
 */
@Getter
@Setter
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String rfamAcc;
    private String rfam_id;
    private String auto_wiki;
    private String description;
    private String author;
    private String seed_source;
    private String gathering_cutoff;
    private String trusted_cutoff;
    private String noise_cutoff;
    private String comment;
    private String previous_id;
    private String cmbuild;
    private String cmcalibrate;
    private String cmsearch;
    private String num_seed;
    private String num_full;
    private String num_genome_seq;
    private String num_refseq;
    private String type;
    private String structure_source;
    private String number_of_species;
    private String number_3d_structures;
    private String num_pseudonokts;
    private String tax_seed;
    private String ecmli_lambda;
    private String ecmli_mu;
    private String ecmli_cal_db;
    private String ecmli_cal_hits;
    private String maxl;
    private String clen;
    private String match_pair_node;
    private String hmm_tau;
    private String hmm_lambda;
    private String created;
    private String updated;
}
package uk.co.ivandimitrov.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    public String getRfam_acc() {
        return this.rfamAcc;
    }

    public void setRfam_acc(String rfam_acc) {
        this.rfamAcc = rfam_acc;
    }

    public String getRfam_id() {
        return this.rfam_id;
    }

    public void setRfam_id(String rfam_id) {
        this.rfam_id = rfam_id;
    }

    public String getAuto_wiki() {
        return this.auto_wiki;
    }

    public void setAuto_wiki(String auto_wiki) {
        this.auto_wiki = auto_wiki;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeed_source() {
        return this.seed_source;
    }

    public void setSeed_source(String seed_source) {
        this.seed_source = seed_source;
    }

    public String getGathering_cutoff() {
        return this.gathering_cutoff;
    }

    public void setGathering_cutoff(String gathering_cutoff) {
        this.gathering_cutoff = gathering_cutoff;
    }

    public String getTrusted_cutoff() {
        return this.trusted_cutoff;
    }

    public void setTrusted_cutoff(String trusted_cutoff) {
        this.trusted_cutoff = trusted_cutoff;
    }

    public String getNoise_cutoff() {
        return this.noise_cutoff;
    }

    public void setNoise_cutoff(String noise_cutoff) {
        this.noise_cutoff = noise_cutoff;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPrevious_id() {
        return this.previous_id;
    }

    public void setPrevious_id(String previous_id) {
        this.previous_id = previous_id;
    }

    public String getCmbuild() {
        return this.cmbuild;
    }

    public void setCmbuild(String cmbuild) {
        this.cmbuild = cmbuild;
    }

    public String getCmcalibrate() {
        return this.cmcalibrate;
    }

    public void setCmcalibrate(String cmcalibrate) {
        this.cmcalibrate = cmcalibrate;
    }

    public String getCmsearch() {
        return this.cmsearch;
    }

    public void setCmsearch(String cmsearch) {
        this.cmsearch = cmsearch;
    }

    public String getNum_seed() {
        return this.num_seed;
    }

    public void setNum_seed(String num_seed) {
        this.num_seed = num_seed;
    }

    public String getNum_full() {
        return this.num_full;
    }

    public void setNum_full(String num_full) {
        this.num_full = num_full;
    }

    public String getNum_genome_seq() {
        return this.num_genome_seq;
    }

    public void setNum_genome_seq(String num_genome_seq) {
        this.num_genome_seq = num_genome_seq;
    }

    public String getNum_refseq() {
        return this.num_refseq;
    }

    public void setNum_refseq(String num_refseq) {
        this.num_refseq = num_refseq;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStructure_source() {
        return this.structure_source;
    }

    public void setStructure_source(String structure_source) {
        this.structure_source = structure_source;
    }

    public String getNumber_of_species() {
        return this.number_of_species;
    }

    public void setNumber_of_species(String number_of_species) {
        this.number_of_species = number_of_species;
    }

    public String getNumber_3d_structures() {
        return this.number_3d_structures;
    }

    public void setNumber_3d_structures(String number_3d_structures) {
        this.number_3d_structures = number_3d_structures;
    }

    public String getNum_pseudonokts() {
        return this.num_pseudonokts;
    }

    public void setNum_pseudonokts(String num_pseudonokts) {
        this.num_pseudonokts = num_pseudonokts;
    }

    public String getTax_seed() {
        return this.tax_seed;
    }

    public void setTax_seed(String tax_seed) {
        this.tax_seed = tax_seed;
    }

    public String getEcmli_lambda() {
        return this.ecmli_lambda;
    }

    public void setEcmli_lambda(String ecmli_lambda) {
        this.ecmli_lambda = ecmli_lambda;
    }

    public String getEcmli_mu() {
        return this.ecmli_mu;
    }

    public void setEcmli_mu(String ecmli_mu) {
        this.ecmli_mu = ecmli_mu;
    }

    public String getEcmli_cal_db() {
        return this.ecmli_cal_db;
    }

    public void setEcmli_cal_db(String ecmli_cal_db) {
        this.ecmli_cal_db = ecmli_cal_db;
    }

    public String getEcmli_cal_hits() {
        return this.ecmli_cal_hits;
    }

    public void setEcmli_cal_hits(String ecmli_cal_hits) {
        this.ecmli_cal_hits = ecmli_cal_hits;
    }

    public String getMaxl() {
        return this.maxl;
    }

    public void setMaxl(String maxl) {
        this.maxl = maxl;
    }

    public String getClen() {
        return this.clen;
    }

    public void setClen(String clen) {
        this.clen = clen;
    }

    public String getMatch_pair_node() {
        return this.match_pair_node;
    }

    public void setMatch_pair_node(String match_pair_node) {
        this.match_pair_node = match_pair_node;
    }

    public String getHmm_tau() {
        return this.hmm_tau;
    }

    public void setHmm_tau(String hmm_tau) {
        this.hmm_tau = hmm_tau;
    }

    public String getHmm_lambda() {
        return this.hmm_lambda;
    }

    public void setHmm_lambda(String hmm_lambda) {
        this.hmm_lambda = hmm_lambda;
    }

    public String getCreated() {
        return this.created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return this.updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

}